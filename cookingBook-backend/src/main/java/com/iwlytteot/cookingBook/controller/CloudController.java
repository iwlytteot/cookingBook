package com.iwlytteot.cookingBook.controller;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@Component
@Data
public class CloudController {
    private AccessToken token;

    public CloudController() {
        try {
            GoogleCredentials credential = GoogleCredentials.fromStream(new FileInputStream("censored"))
                    .createScoped(Collections.singleton("https://www.googleapis.com/auth/drive"));

            credential.refresh();
            token = credential.getAccessToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
