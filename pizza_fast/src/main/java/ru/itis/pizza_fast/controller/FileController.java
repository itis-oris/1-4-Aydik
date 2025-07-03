package ru.itis.pizza_fast.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.pizza_fast.service.S3Service;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        s3Service.uploadFile(file.getOriginalFilename(), file.getBytes());
        return ResponseEntity.ok("Uploaded " + file.getOriginalFilename());
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {
        try {
            byte[] data = s3Service.downloadFile(filename);
            return ResponseEntity.ok().body(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
