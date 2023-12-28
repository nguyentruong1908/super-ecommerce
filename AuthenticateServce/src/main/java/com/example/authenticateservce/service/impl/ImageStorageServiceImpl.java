package com.example.authenticateservce.service.impl;

import com.example.authenticateservce.service.ImageStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {
    private final Path storageFolder = Paths.get("upload");

    public ImageStorageServiceImpl() {
        try {
            Files.createDirectories(storageFolder);
        } catch (IOException e) {
            throw new RuntimeException("Cannot initialize storage", e);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            if (!isImageFile(file)) {
                throw new RuntimeException("You can only upload image file");
            }
            float fileSizeInMegaBytes = file.getSize() / 1_000_000.0f;
            if (fileSizeInMegaBytes > 5.0f) {
                throw new RuntimeException("File must be <= 5Mb");
            }
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName = generatedFileName + "." + fileExtension;
            Path destinationPathFile = this.storageFolder.resolve(Paths.get(generatedFileName)).normalize().toAbsolutePath();
            if (!destinationPathFile.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationPathFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file ", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public byte[] readFileContent(String fileName) {
        try {
            Path file = storageFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            } else {
                throw new RuntimeException("Could not read file: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + fileName, e);
        }
    }

    @Override
    public void deleteFile(String filename) {
        try {
            // Xác định đường dẫn đầy đủ của tập tin
            Path filePath = storageFolder.resolve(filename);

            // Xóa tập tin nếu tồn tại
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                System.out.println("Tập tin đã được xóa thành công.");
            } else {
                System.out.println("Tập tin không tồn tại.");
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi xóa tập tin: " + e.getMessage());
        }
    }

    private boolean isImageFile(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"png", "jpg", "jpeg", "bmp"}).contains(fileExtension.trim().toLowerCase());
    }
}
