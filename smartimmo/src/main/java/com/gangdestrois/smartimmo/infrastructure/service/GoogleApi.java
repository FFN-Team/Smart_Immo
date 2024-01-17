package com.gangdestrois.smartimmo.infrastructure.service;

import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.GoogleUnauthorizedException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.SecretsNotFoundExceptionException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.DriveScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

@Component
public class GoogleApi {

    @Value("${google.api.key.path}")
    private Resource secretKeyPath;
    private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    public GoogleApi() {
        OAuth2Utils oAuth2Utils = new OAuth2Utils();
    }

    public Credential getCredentials(Set<String> scopes, HttpTransport httpTransport, GsonFactory jsonFactory) {
        if (isNull(secretKeyPath))
            throw new SecretsNotFoundExceptionException("secretKeyPath", "Google API Secrets not found.");
        GoogleClientSecrets secrets;
        try {
            secrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(secretKeyPath.getInputStream()));
        } catch (IOException e) {
            throw new SecretsNotFoundExceptionException(secretKeyPath.getFilename(), "Google API Secrets not found.");
        }
        try {
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, jsonFactory, secrets, scopes)
                    .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                    .setAccessType("offline")
                    .build();

            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        } catch (IOException e) {
            throw new GoogleUnauthorizedException(secretKeyPath.getFilename(), "Google API unauthorized");
        }
    }

    public static Credential getCredentials2(List<String> scopes, final NetHttpTransport netHttpTransport) throws IOException {
        InputStream in = GoogleApi.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (isNull(in)) {
            throw new FileNotFoundException("Resource not found " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(netHttpTransport, JSON_FACTORY, clientSecrets,
                scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
}
