package com.gangdestrois.smartimmo.infrastructure.service;

import com.gangdestrois.smartimmo.domain.document.Folder;
import com.gangdestrois.smartimmo.domain.document.port.DocumentService;
import com.gangdestrois.smartimmo.infrastructure.tool.ApplicationData;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class GoogleDriveApi implements DocumentService {
    private static Drive drive;

    @Autowired
    public GoogleDriveApi() {
    }

    private static void initializeDrive() {
        if (nonNull(drive)) return;
        NetHttpTransport httpTransport;
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            drive = new Drive.Builder(
                    httpTransport,
                    GsonFactory.getDefaultInstance(),
                    GoogleApi.getCredentials(httpTransport))
                    .setApplicationName(ApplicationData.TECHNIMMO)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

/*    private Credential authorize() throws IOException {
        return new GoogleCredential.Builder()
                .setJsonFactory(GsonFactory.getDefaultInstance())
                .setTransport(new NetHttpTransport())
                .setClientSecrets("CLIENT_ID", "CLIENT_SECRET")
                .build()
                .setRefreshToken("REFRESH_TOKEN");
    }*/

    public String uploadFile(java.io.File file, String fileName, String fileType) {
        initializeDrive();
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        return saveFileInDrive(file, fileType, fileMetadata);
    }

    private String saveFileInDrive(java.io.File file, String fileType, File fileMetadata) {
        initializeDrive();
        FileContent mediaContent = new FileContent(fileType, file);
        File uploadFile;
        try {
            uploadFile = drive.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            return uploadFile.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadFileIntoFolder(java.io.File filePath, String fileName, String fileType, String folderId) {
        initializeDrive();
        File fileMetadata = new File();
        fileMetadata.setName(fileName);

        if (folderId != null && !folderId.isEmpty()) {
            List<String> parents = Collections.singletonList(folderId);
            fileMetadata.setParents(parents);
        }

        return saveFileInDrive(filePath, fileType, fileMetadata);
    }


    public Folder createFolder(String folderName) {
        initializeDrive();
        File folderMetadata = new File();
        folderMetadata.setName(folderName);
        folderMetadata.setMimeType("application/vnd.google-apps.folder");
        File folder;
        try {
            folder = drive.files().create(folderMetadata)
                    .setFields("id, name, webViewLink, webContentLink, parents")
                    .execute();
            return toFolderModel(folder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteFile(String fileId) {
        initializeDrive();
        try {
            drive.files().delete(fileId).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public com.gangdestrois.smartimmo.domain.document.File generatePublicUrl(String fileId) {
        initializeDrive();
        Permission permission = new Permission();
        permission.setRole("reader");
        permission.setType("anyone");
        try {
            drive.permissions().create(fileId, permission).execute();
            File file = drive.files().get(fileId)
                    .setFields("id, name, webViewLink, webContentLink, parents").execute();
            return toFileModel(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public com.gangdestrois.smartimmo.domain.document.File toFileModel(File file) {
        return new com.gangdestrois.smartimmo.domain.document.File(file.getId(), file.getName(),
                file.getWebContentLink(), file.getWebViewLink());
    }

    public Folder toFolderModel(File file) {
        return new Folder(file.getId(), file.getName(), file.getWebContentLink(), file.getWebViewLink());
    }
}
