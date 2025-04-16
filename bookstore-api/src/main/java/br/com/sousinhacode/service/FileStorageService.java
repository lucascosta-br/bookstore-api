package br.com.sousinhacode.service;

import br.com.sousinhacode.config.FileStorageConfig;
import br.com.sousinhacode.exception.FileNotFoundException;
import br.com.sousinhacode.exception.FileStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageService.class);

    private final Path fileStorageLocation;

    public FileStorageService(FileStorageConfig fileStorageConfig) {
        Path path = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath()
                .normalize();

        this.fileStorageLocation = path;
        try {
            LOGGER.info("Creating Directories");

            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            LOGGER.error("Could not create the directory where files will be stored!!");

            throw new FileStorageException("Could not create the directory where files will be stored!!", e);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if (fileName.contains("..")) {
                LOGGER.error("Sorry! Filename Contains a Invalid path Sequence " + fileName);

                throw new FileStorageException("Sorry! Filename Contains a Invalid path Sequence " + fileName);
            }

            LOGGER.info("Saving file in Disk");

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

        } catch (Exception e) {
            LOGGER.error("Could not store file " + fileName + ". Please try Again!");

            throw new FileStorageException("Could not store file " + fileName + ". Please try Again!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            LOGGER.info("Providing download");

            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                LOGGER.error("File not found " + fileName);

                throw new FileNotFoundException("File not found " + fileName);
            }

        } catch (Exception e) {
            LOGGER.error("File not found " + fileName, e);

            throw new FileNotFoundException("File not found " + fileName, e);
        }
    }

}
