package com.iwlytteot.cookingBook.controller;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.iwlytteot.cookingBook.exception.ImageProcessingException;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Collections;

@Component
@Data
public class CloudController {
    private AccessToken token;
    private GoogleCredentials credential;

    public CloudController() {
        try {
            credential = GoogleCredentials.fromStream(new FileInputStream("censored"))
                    .createScoped(Collections.singleton("https://www.googleapis.com/auth/drive"));

            credential.refresh();
            token = credential.getAccessToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadFile(MultipartFile inputFile) throws IOException {
        credential.refreshIfExpired();

        Drive service = new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credential))
                .build();

        java.io.File tempFile = new java.io.File(inputFile.getOriginalFilename());

        if (!Files.exists(tempFile.toPath()) && !tempFile.createNewFile()) {
            throw new ImageProcessingException("File could not be temporarily created");
        }

        try (OutputStream os = new FileOutputStream(tempFile)) {
            os.write(inputFile.getBytes());
        }

        File fileMetadata = new File();
        fileMetadata.setName(inputFile.getOriginalFilename());
        fileMetadata.setParents(Collections.singletonList("1COn2NIaACAyGiodct6LhxjMwzOzkKVP5")); //hardwired folder ID, TODO: might find better solution
        FileContent mediaContent = new FileContent(null, tempFile);
        try {
            File file = service.files().create(fileMetadata, mediaContent)
                    .setFields("id, permissions")
                    .execute();

            var permission = new Permission();
            permission.setRole("reader");
            permission.setType("anyone");

            file.getPermissions().add(permission);
            service.permissions().create(file.getId(), permission).execute();

            return file.getId();
        } catch (GoogleJsonResponseException ex) {
            throw new ImageProcessingException("File upload error: " + ex.getMessage());
        }
    }
}
