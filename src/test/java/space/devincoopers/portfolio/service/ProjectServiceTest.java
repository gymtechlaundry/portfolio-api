//package space.devincoopers.portfolio.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import space.devincoopers.portfolio.client.CdnServiceClient;
//import space.devincoopers.portfolio.client.CdnServiceClientImpl;
//import space.devincoopers.portfolio.mapper.PortfolioMapper;
//import space.devincoopers.portfolio.model.Project;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;

//class ProjectServiceTest {
//
//    private PortfolioMapper portfolioMapper;
//    private ProjectService projectService;
//    private CdnServiceClientImpl cdnServiceClient;
//
//    @BeforeEach
//    void setup() {
//        portfolioMapper = mock(PortfolioMapper.class);
//        projectService = new ProjectService(portfolioMapper, cdnServiceClient);
//    }
//
//    @Test
//    void getAllProjects_returnsProjects() {
//        // Arrange
//        Project project = new Project();
//        project.setId(1L);
//        project.setName("AIHG App");
//
//        when(portfolioMapper.getAllProjects()).thenReturn(Collections.singletonList(project));
//
//        // Act
//        List<Project> result = projectService.getAllProjects();
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals("AIHG App", result.get(0).getName());
//    }
//
//    @Test
//    void getAllProjects_returnsEmptyList() {
//        // Arrange
//        when(portfolioMapper.getAllProjects()).thenReturn(Collections.emptyList());
//
//        // Act
//        List<Project> result = projectService.getAllProjects();
//
//        // Assert
//        assertNotNull(result);
//        assertTrue(result.isEmpty());
//    }
//}