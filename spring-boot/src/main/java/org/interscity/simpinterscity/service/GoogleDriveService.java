package org.interscity.simpinterscity.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.interscity.simpinterscity.util.GoogleDriveUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

@Service
public class GoogleDriveService {
	
	@Value("${upload.dir.drive}")
	private String googleFolderIdParent;

	private File createGoogleFile(String googleFolderIdParent, String contentType, //
			String customFileName, AbstractInputStreamContent uploadStreamContent)
			throws IOException, GeneralSecurityException {
		File fileMetadata = new File();
		fileMetadata.setName(customFileName);
		List<String> parents = Arrays.asList(googleFolderIdParent);
		fileMetadata.setParents(parents);
		Drive driveService = GoogleDriveUtil.getDriveService();
		File file = driveService.files().create(fileMetadata, uploadStreamContent)
				.setFields("id, webContentLink, webViewLink, parents").execute();
		return file;
	}

	public File upload(String contentType, String customFileName, InputStream inputStream)
			throws IOException, GeneralSecurityException {
		AbstractInputStreamContent uploadStreamContent = new InputStreamContent(contentType, inputStream);
		return createGoogleFile(googleFolderIdParent, contentType, customFileName, uploadStreamContent);
	}
}
