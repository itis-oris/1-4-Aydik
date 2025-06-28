package ru.itis.pizza_fast.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${cloud.yandex.bucket-name}")
    private String bucket;

    public void uploadFile(String key, byte[] content) {
        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .contentType("image/jpeg")
                            .build(),
                    RequestBody.fromBytes(content)
            );
            log.info("Successfully uploaded file: {}", key);
        } catch (S3Exception e) {
            log.error("Failed to upload file: {}. Error: {}", key, e.getMessage());
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    public byte[] downloadFile(String key) throws IOException {
        try (ResponseInputStream<GetObjectResponse> responseInputStream = s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build()
        )) {
            byte[] content = responseInputStream.readAllBytes();
            log.info("Successfully downloaded file: {}", key);
            return content;
        } catch (S3Exception e) {
            log.error("Failed to download file: {}. Error: {}", key, e.getMessage());
            throw new IOException("Failed to download file: " + e.getMessage(), e);
        }
    }


    public List<S3Object> listObjects() {
        try {
            ListObjectsV2Response list = s3Client.listObjectsV2(ListObjectsV2Request.builder()
                    .bucket(bucket)
                    .build());
            log.info("Successfully listed objects in bucket: {}", bucket);
            return list.contents();
        } catch (S3Exception e) {
            log.error("Failed to list objects in bucket: {}. Error: {}", bucket, e.getMessage());
            throw new RuntimeException("Failed to list objects: " + e.getMessage(), e);
        }
    }
}
