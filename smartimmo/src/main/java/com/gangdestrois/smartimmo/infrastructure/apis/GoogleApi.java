package com.gangdestrois.smartimmo.infrastructure.apis;

import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.GoogleUnauthorizedException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.SecretsNotFoundExceptionException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Set;

import static java.util.Objects.isNull;

@Component
public class GoogleApi {

    @Value("${google.api.key.path}")
    private Resource secretKeyPath;

    public GoogleApi() {
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
}
