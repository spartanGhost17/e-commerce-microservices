package com.ecommerce.product.service;

import static com.ecommerce.product.constants.ApplicationConstants.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    public String saveProductImage(MultipartFile file, String productUUID) {
        Path fileStorageLocation = Paths.get(System.getProperty("user.home") + PRODUCTS_IMAGES_LOCATION + "/"+ productUUID).toAbsolutePath().normalize();
        if(!Files.exists(fileStorageLocation)) {
            try {
                log.info("creating directory it does not exist");
                Files.createDirectories(fileStorageLocation);
            } catch (Exception exception) {
                log.info("random error");
                log.error(exception.getMessage());
                throw new RuntimeException("Could not create directories to save product images");
            }
            log.info("Created image directories");
        }

        // Generate a unique image UUID for the filename
        String imageUUID = UUID.randomUUID().toString();
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String newFileName = imageUUID + (fileExtension != null ? fileExtension : ".png");

        // Define the target location where the file will be saved
        Path targetLocation = fileStorageLocation.resolve(newFileName);

        try {
            // Save the file to the target location
            file.transferTo(targetLocation.toFile());
            log.info("File saved successfully: {}", targetLocation.toString());
            return productUUID+"/"+newFileName;//targetLocation.toString();
        } catch (IOException e) {
            log.error("Could not save the file. Error: {}", e.getMessage(), e);
            throw new RuntimeException("Could not save the file. Error: " + e.getMessage(), e);
        }

        /*try {
            // Create directories if they do not exist
            //Files.createDirectories(fileStorageLocation);

            // Determine the target location where the file will be stored
            Path targetLocation = fileStorageLocation.resolve(Objects.requireNonNull(file.getOriginalFilename()));

            // Save the file
            file.transferTo(targetLocation.toFile());

            System.out.println("File saved successfully: " + targetLocation.toString());
            return targetLocation.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not save the file. Error: " + e.getMessage(), e);
        }*/
    }

    /**
     * get the image bytes
     * @param folderName the folder name
     * @param fileName the file name
     * @return the bytes
     */
    public byte[] getProductImage(String folderName, String fileName) {
        try {
            return Files.readAllBytes(Paths.get(System.getProperty("user.home") + PRODUCTS_IMAGES_LOCATION +"/"+folderName+"/"+fileName));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * delete single product image
     * @param fileName the file name
     * @param folderName the folder name
     */
    public void deleteProductImage(String fileName, String folderName) {
        Path fileStorageLocation = Paths.get(System.getProperty("user.home") + PRODUCTS_IMAGES_LOCATION+"/"+folderName+"/"+fileName).toAbsolutePath().normalize();
        try {
            Files.delete(fileStorageLocation);
            System.out.println("File deleted successfully: " + fileStorageLocation.toString());
        } catch (NoSuchFileException e) {
            System.out.println("File not found: " + fileStorageLocation.toString());
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty: " + fileStorageLocation.toString());
        } catch (IOException e) {
            System.out.println("Unable to delete file due to an I/O error: " + e.getMessage());
        }
    }

    /**
     * delete product images
     * @param folderName the folder name
     */
    public void deleteAllProductImages(String folderName) {
        log.info("Product images folder to delete {}",folderName);
        Path fileStorageLocation = Paths.get(System.getProperty("user.home") + PRODUCTS_IMAGES_LOCATION+"/"+folderName).toAbsolutePath().normalize();
        log.info("Try to all product images {}", fileStorageLocation);
        try {
            boolean success = deleteDirectory(fileStorageLocation.toFile());
            if (success) {
                log.info("Product images Folder deleted successfully.");
            } else {
                log.info("Error deleting the folder.");
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * delete the folder including files
     * @param directoryToBeDeleted the directory to delete
     * @return true if deleted successfully
     */
    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex > 0) ? fileName.substring(dotIndex) : null;
    }

}
