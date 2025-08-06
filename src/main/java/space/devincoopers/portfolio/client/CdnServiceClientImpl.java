package space.devincoopers.portfolio.client;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CdnServiceClientImpl implements CdnServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(CdnServiceClientImpl.class);

    @Value("${cdn.public.url}")
    private String cdnPublicUrl;

    @Value("${cdn.api.url}")
    private String cdnApiUrl;

    @Value("${cdn.api.key}")
    private String cdnApiKey;

    private final RestTemplate restTemplate;

    @Override
    public String uploadImage(String appName, MultipartFile file) throws IOException {
        String uploadUrl = cdnApiUrl + "/api/images/" + appName;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("CDN_API_KEY", cdnApiKey);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputResource(file));

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uploadUrl, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String relativePath = response.getBody(); // "portfolio-api/uuid-file.jpg"
            logger.info("Response inside client: {}", relativePath);
            return cdnPublicUrl + "/" + relativePath;
        }

        throw new RuntimeException("❌ Failed to upload image to CDN. Status: " + response.getStatusCode());
    }

    @Override
    public void deleteImage(String appName, String imageUrl) {
        try {
            // Remove public CDN base URL to get relative path
            String relativePath = imageUrl.replaceFirst(cdnPublicUrl + "/", "");
            String[] parts = relativePath.split("/", 2);

            if (parts.length != 2 || !parts[0].equals(appName)) {
                throw new IllegalArgumentException("Invalid image URL: " + imageUrl);
            }

            String filename = parts[1];
            String deleteUrl = cdnApiUrl + "/api/images/" + appName + "/" + filename;

            HttpHeaders headers = new HttpHeaders();
            headers.set("CDN_API_KEY", cdnApiKey);

            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<Void> response = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, request, Void.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("❌ Failed to delete image from CDN. Status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to delete image from CDN: " + imageUrl, e);
        }
    }
}
