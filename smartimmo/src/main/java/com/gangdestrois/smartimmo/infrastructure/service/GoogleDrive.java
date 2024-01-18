package com.gangdestrois.smartimmo.infrastructure.service;

import com.gangdestrois.smartimmo.domain.tool.ApplicationData;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;

import java.io.IOException;
import java.util.List;

public class GoogleDrive {
    private static final String FILE_ID = "1bdCRsOLgTrbWu6Abb2ZJFx2_TloT5Rbw";
    private Drive drive;

    public GoogleDrive() throws Exception {
        this.drive = initializeDrive();
    }

    private Drive initializeDrive() throws Exception {
        var httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Drive.Builder(
                httpTransport,
                GsonFactory.getDefaultInstance(),
                GoogleApi.getCredentials2(List.of(DriveScopes.DRIVE_FILE), httpTransport))
                .setApplicationName(ApplicationData.TECHNIMMO)
                .build();
    }

    private static Credential authorize() throws IOException {
        return new GoogleCredential.Builder()
                .setJsonFactory(GsonFactory.getDefaultInstance())
                .setTransport(new NetHttpTransport())
                .setClientSecrets("CLIENT_ID", "CLIENT_SECRET")
                .build()
                .setRefreshToken("REFRESH_TOKEN");
    }

    public String uploadFile(String stringFilePath, String fileName) throws Exception {
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        java.io.File filePath = new java.io.File(stringFilePath);
        FileContent mediaContent = new FileContent("image/jpg", filePath);
        File file = drive.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();
        return file.getId();
    }

    public void deleteFile(String fileId) throws IOException {
        drive.files().delete(fileId).execute();
    }

    public File generatePublicUrl(Drive drive) throws IOException {
        Permission permission = new Permission();
        permission.setRole("reader");
        permission.setType("anyone");
        drive.permissions().create(FILE_ID, permission).execute();

        File file = drive.files().get(FILE_ID).setFields("webViewLink, webContentLink").execute();
        System.out.println("webViewLink: " + file.getWebViewLink());
        System.out.println("webContentLink: " + file.getWebContentLink());
        return file;
    }
}
