package com.example.dolphintalkserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AmazonS3Config {
    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;   // 액세스 키

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;   // 비밀 키

    @Bean
    public S3Client amazonS3Client() {
        StaticCredentialsProvider staticCredentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));

        Region region = Region.AP_NORTHEAST_2;

        return S3Client.builder()
                .region(region)
                .credentialsProvider(staticCredentialsProvider)
                .build();
    }
}
