package space.devincoopers.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import space.devincoopers.portfolio.client.CdnServiceClient;
import space.devincoopers.portfolio.mapper.PortfolioMapper;
import space.devincoopers.portfolio.model.Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    private final PortfolioMapper portfolioMapper;
    private final CdnServiceClient cdnServiceClient;

    private static final String APP_NAME = "portfolio";

    public List<Project> getAllProjects() {
        return portfolioMapper.getAllProjects();
    }

    public Project getProjectById(Long id) {
        return portfolioMapper.getProjectById(id);
    }

    public void insertProject(Project project) {
        portfolioMapper.insertProject(project);
    }

    public void updateProject(Project project) {
        portfolioMapper.updateProject(project);
    }

    public void deleteProjectById(Long id) {
        portfolioMapper.deleteProjectById(id);
    }

    public void createProjectWithUploads(Project project, MultipartFile icon, MultipartFile[] screenshots) throws IOException {
        if (icon != null && !icon.isEmpty()) {
            String iconPath = cdnServiceClient.uploadImage(APP_NAME + "/icon", icon);
            project.setIcon(iconPath);
        }

        if (screenshots != null) {
            List<String> uploadedScreenshots = new ArrayList<>();
            for (MultipartFile file: screenshots) {
                if (!file.isEmpty()) {
                    String path = cdnServiceClient.uploadImage(APP_NAME + "/screenshots", file);
                    uploadedScreenshots.add(path);
                }
            }
            project.setScreenshots(uploadedScreenshots);
        }

        insertProject(project);
    }

    public void updateProjectWithUploads(Project project, MultipartFile icon, MultipartFile[] screenshots) throws IOException {
        Project existing = getProjectById(project.getId());

        // Delete and replace icon if updated
        if (icon != null && !icon.isEmpty()) {
            if (existing.getIcon() != null) {
                cdnServiceClient.deleteImage(APP_NAME, extractFilename(existing.getIcon()));
            }
            String iconPath = cdnServiceClient.uploadImage(APP_NAME, icon);
            project.setIcon(iconPath);
        } else {
            project.setIcon(existing.getIcon());
        }

        // Delete and replace screenshots if updated
        if (screenshots != null && screenshots.length > 0) {
            if (existing.getScreenshots() != null) {
                for (String path : existing.getScreenshots()) {
                    cdnServiceClient.deleteImage(APP_NAME, extractFilename(path));
                }
            }

            List<String> uploadedScreenshots = new ArrayList<>();
            for (MultipartFile file : screenshots) {
                if (!file.isEmpty()) {
                    String path = cdnServiceClient.uploadImage(APP_NAME, file);
                    uploadedScreenshots.add(path);
                }
            }
            project.setScreenshots(uploadedScreenshots);
        } else {
            project.setScreenshots(existing.getScreenshots());
        }

        updateProject(project);
    }

    public void deleteProjectWithAssets(Long id) {
        Project existing = getProjectById(id);
        if (existing == null) return;

        if (existing.getIcon() != null) {
            cdnServiceClient.deleteImage(APP_NAME, extractFilename(existing.getIcon()));
        }

        if (existing.getScreenshots() != null && !existing.getScreenshots().isEmpty()) {
            logger.error("❌ inside if existing screenshots");
            for (String path : existing.getScreenshots()) {
                cdnServiceClient.deleteImage(APP_NAME, extractFilename(path));
            }
        }

        logger.error("❌ not inside if existing screenshots");

        deleteProjectById(id);
    }

    private String extractFilename(String path) {
        if (path == null) return "";
        // Example: /cdn/portfolio/uuid-filename.jpg → portfolio/uuid-filename.jpg
        return path.replaceFirst("/cdn/", "");
    }
}
