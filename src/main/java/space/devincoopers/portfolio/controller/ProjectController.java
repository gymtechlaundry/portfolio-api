package space.devincoopers.portfolio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import space.devincoopers.portfolio.client.CdnServiceClient;
import space.devincoopers.portfolio.model.Project;
import space.devincoopers.portfolio.service.ProjectService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return project != null ? ResponseEntity.ok(project) : ResponseEntity.notFound().build();
    }

    @PutMapping(consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateProject(
            @RequestPart("project") String projectJson,
            @RequestPart("icon") MultipartFile icon,
            @RequestPart("screenshots") MultipartFile[] screenshots) throws IOException {

        Project project = objectMapper.readValue(projectJson, Project.class);

        projectService.updateProjectWithUploads(project, icon, screenshots);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createProject(
            @RequestPart("project") String projectJson,
            @RequestPart("icon")MultipartFile icon,
            @RequestPart("screenshots") MultipartFile[] screenshots) throws IOException {

        logger.debug("Did I make it in here");
        Project project = objectMapper.readValue(projectJson, Project.class);

        projectService.createProjectWithUploads(project, icon, screenshots);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProjectWithAssets(id);
        return ResponseEntity.noContent().build();
    }
}
