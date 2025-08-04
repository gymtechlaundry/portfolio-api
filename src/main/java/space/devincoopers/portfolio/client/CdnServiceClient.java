package space.devincoopers.portfolio.client;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CdnServiceClient {
    String uploadImage(String appName, MultipartFile file) throws IOException;
    void deleteImage(String appName, String filename);
}