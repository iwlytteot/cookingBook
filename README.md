# Cooking book - backend

Simple backend service for managing food recipes.

Endpoint documentation is available on /swagger-ui/index.html

## Get started
1) Java 19 or newer
2) Maven

## How to set environment
1) Edit src/main/resources/application.properties *
2) Create Google Drive Service account, create new key and store it in JSON format. Rename your JSON file into *credentials.json* and store it in src/main/resources

\* Either use https://github.com/ulisesbocchio/jasypt-spring-boot for password encryption (recommended option) or put raw values into *spring.datasource.password*
