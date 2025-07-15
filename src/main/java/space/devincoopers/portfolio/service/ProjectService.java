package space.devincoopers.portfolio.service;

import org.springframework.stereotype.Service;
import space.devincoopers.portfolio.mapper.PortfolioMapper;
import space.devincoopers.portfolio.model.Project;

import java.util.List;

@Service
public class ProjectService {
    private final PortfolioMapper portfolioMapper;

    public ProjectService(PortfolioMapper portfolioMapper) {
        this.portfolioMapper = portfolioMapper;
    }

    public List<Project> getAllProjects() {
        return portfolioMapper.getAllProjects();
    }
}
