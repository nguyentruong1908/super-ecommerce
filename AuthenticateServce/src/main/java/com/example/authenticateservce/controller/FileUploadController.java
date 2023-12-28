package com.example.authenticateservce.controller;

import com.example.authenticateservce.dto.UserDto;
import com.example.authenticateservce.dto.UserSaveDto;
import com.example.authenticateservce.entity.UserEntity;
import com.example.authenticateservce.service.ImageStorageService;
import com.example.authenticateservce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileUploadController {

    private final ModelMapper modelMapper;

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public String addFile(@RequestParam("file")MultipartFile file) {
        try {
            String generateFileName = imageStorageService.storeFile(file);
            return generateFileName;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @PostMapping("/avatar_upload")
    public String uploadAvatar(@RequestParam("file")MultipartFile file, @RequestParam("uid") Long uid) {
        try {
            String generateFileName = imageStorageService.storeFile(file);
            UserDto dto = userService.findById(uid);
            dto.setAvatarUrl(generateFileName);
            userService.update(uid, modelMapper.map(dto, UserSaveDto.class));
            return generateFileName;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = imageStorageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{fileName:.+}")
    public void DeleteFile(@PathVariable String fileName) {
        imageStorageService.deleteFile(fileName);
    }

}
