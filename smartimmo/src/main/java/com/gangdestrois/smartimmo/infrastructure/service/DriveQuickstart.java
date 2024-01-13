package com.gangdestrois.smartimmo.infrastructure.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/* class to demonstrate use of Drive files list API */
public class DriveQuickstart {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();


    public static void main(String... args) throws IOException, GeneralSecurityException {

        // Initialiser le service Google Drive
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT,
                JSON_FACTORY,
                GoogleApi.getCredentials2(List.of(DriveScopes.DRIVE_FILE), HTTP_TRANSPORT))
                .setApplicationName("TechnImmo")
                .build();

        // Requête pour récupérer tous les fichiers
        FileList result = service.files().list().execute();

        // Récupérer la liste de fichiers
        List<File> files = result.getFiles();

        // Afficher les détails des fichiers
        if (files == null || files.isEmpty()) {
            System.out.println("Réponse complète : " + result.toPrettyString());
        } else {
            System.out.println("Fichiers :");
            for (File file : files) {
                System.out.printf("Nom : %s, ID : %s\n", file.getName(), file.getId());
            }
        }
    }
}