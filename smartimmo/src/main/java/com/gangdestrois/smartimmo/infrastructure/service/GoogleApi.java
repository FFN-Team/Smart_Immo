package com.gangdestrois.smartimmo.infrastructure.service;

import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.SecretsNotFoundExceptionException;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

import static com.google.api.services.drive.DriveScopes.DRIVE;
import static com.google.api.services.drive.DriveScopes.DRIVE_FILE;
import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static java.util.Objects.isNull;

@Component
public class GoogleApi {

    @Value("${google.api.key.path}")
    private static Resource secretKeyPath;
    private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow;

    public GoogleApi() {
    }

    // possibilité de mettre une strategy
    public static Credential getCredentialsWithEnvironmentsVariables(List<String> scopes, HttpTransport httpTransport)
            throws IOException {
        if (isNull(secretKeyPath))
            throw new SecretsNotFoundExceptionException("secretKeyPath", "Google API Secrets not found.");
        GoogleClientSecrets secrets;
        try {
            secrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(secretKeyPath.getInputStream()));
        } catch (IOException e) {
            throw new SecretsNotFoundExceptionException(secretKeyPath.getFilename(), e.getMessage());
        }
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, secrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static Credential getCredentials(NetHttpTransport netHttpTransport) throws IOException {
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        if(isNull(googleAuthorizationCodeFlow)) getCredentialsWithClientSecretFile(
                List.of(GMAIL_SEND, DRIVE_FILE, DRIVE), netHttpTransport);
        return new AuthorizationCodeInstalledApp(googleAuthorizationCodeFlow, receiver).authorize("user");
    }

    public static void getCredentialsWithClientSecretFile(List<String> scopes, NetHttpTransport netHttpTransport)
            throws IOException {
        InputStream in = GoogleApi.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (isNull(in)) {
            throw new FileNotFoundException("Resource not found " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(netHttpTransport, JSON_FACTORY,
                clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
    }
}
