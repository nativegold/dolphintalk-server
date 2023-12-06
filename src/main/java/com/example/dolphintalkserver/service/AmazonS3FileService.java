package com.example.dolphintalkserver.service;

import com.example.dolphintalkserver.dto.message.UploadImageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class AmazonS3FileService {
    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    public UploadImageResponseDTO uploadImage(MultipartFile multipartFile) throws IOException {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(multipartFile.getOriginalFilename())
                .contentType(multipartFile.getContentType())
                .build();

        RequestBody requestBody = RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize());
        s3Client.putObject(objectRequest, requestBody);

        return new UploadImageResponseDTO(getUrl(multipartFile.getOriginalFilename()));
    }

    public String getUrl(String keyName) {
        GetUrlRequest urlRequest = GetUrlRequest.builder()
                .bucket(bucket)
                .key(keyName)
                .build();

        URL url = s3Client.utilities().getUrl(urlRequest);

        return url.toString();
    }
}
