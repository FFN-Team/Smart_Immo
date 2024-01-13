package com.gangdestrois.smartimmo.infrastructure.service;

import com.gangdestrois.smartimmo.domain.ApplicationData;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ImporterVersGoogleDrive {

    private static final String APPLICATION_NAME = "VotreNomDeProjet";
    private static final String CHEMIN_VERS_JSON = "/chemin/vers/credentials.json"; // Mettez le chemin vers votre fichier JSON

    public static void main(String[] args) throws IOException {
        // Initialiser le service Google Drive
        NetHttpTransport netHttpTransport = new NetHttpTransport();
        Credential credential = GoogleApi.getCredentials2(List.of(DriveScopes.DRIVE_FILE), netHttpTransport);
        var service = new Drive.Builder(netHttpTransport, GsonFactory.getDefaultInstance(), credential)
                .setApplicationName(ApplicationData.TECHNIMMO)
                .build();

        // Chemin local vers le fichier à importer
        String cheminVersFichierLocal = "/chemin/vers/votre/fichier.txt";

        // Nom que vous souhaitez donner au fichier sur Google Drive
        String nomFichierSurGoogleDrive = "nouveau_fichier.txt";

        // ID du dossier dans Google Drive où vous souhaitez importer le fichier (peut être null pour le dossier racine)
        String idDossierSurGoogleDrive = null;

        // Créer le fichier metadata
        File fichierMetadata = new File();
        fichierMetadata.setName(nomFichierSurGoogleDrive);
        if (idDossierSurGoogleDrive != null) {
            fichierMetadata.setParents(Collections.singletonList(idDossierSurGoogleDrive));
        }

        // Créer le contenu du fichier à partir du fichier local
        java.io.File fichierLocal = new java.io.File(cheminVersFichierLocal);
        FileContent mediaContent = new FileContent("application/octet-stream", fichierLocal);

        // Créer la requête d'import
        Drive.Files.Create request = service.files().create(fichierMetadata, mediaContent);

        // Surveiller la progression de l'upload
        request.getMediaHttpUploader().setProgressListener(new MediaHttpUploaderProgressListener() {
            @Override
            public void progressChanged(MediaHttpUploader uploader) throws IOException {
                switch (uploader.getUploadState()) {
                    case INITIATION_STARTED:
                        System.out.println("Initiation de l'upload...");
                        break;
                    case INITIATION_COMPLETE:
                        System.out.println("Initiation complète.");
                        break;
                    case MEDIA_IN_PROGRESS:
                        System.out.println("En cours d'upload : " + uploader.getProgress());
                        break;
                    case MEDIA_COMPLETE:
                        System.out.println("Upload terminé.");
                        break;
                }
            }
        });

        // Exécuter la requête d'import
        File fichierGoogleDrive = request.execute();

        System.out.println("Fichier importé avec succès sur Google Drive. ID du fichier sur Google Drive : " + fichierGoogleDrive.getId());
    }
}

