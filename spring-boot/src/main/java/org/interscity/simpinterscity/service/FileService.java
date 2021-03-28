package org.interscity.simpinterscity.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.interscity.simpinterscity.exception.FileStorageException;
import org.interscity.simpinterscity.model.Scenario;
import org.interscity.simpinterscity.util.file.FileManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	@Value("${upload.dir.local}")
    public String uploadDir;

    public String uploadFile(Scenario scenario, MultipartFile file) {
        try {
        	FileManager.createDir(new File(uploadDir + File.separator + scenario.getId()));
        	String fileName = uploadDir + File.separator + scenario.getId() + File.separator + StringUtils.cleanPath(file.getOriginalFilename());
            Path copyLocation = Paths
                .get(fileName);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                + ". Please try again!");
        }
    }

}
