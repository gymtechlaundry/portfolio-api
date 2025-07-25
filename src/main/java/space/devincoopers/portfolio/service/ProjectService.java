package space.devincoopers.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.devincoopers.portfolio.mapper.PortfolioMapper;
import space.devincoopers.portfolio.model.Project;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final PortfolioMapper portfolioMapper;

    public List<Project> getAllProjects() {
        return portfolioMapper.getAllProjects();
    }

    public Project getProjectById(Long id) {
        return portfolioMapper.getProjectById(id);
    }

    public void deleteProjectById(Long id) {
        portfolioMapper.deleteProjectById(id);
    }

    public void insertProject(Project project) {
        portfolioMapper.insertProject(project);
    }
}
