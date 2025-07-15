package space.devincoopers.portfolio.mapper;

import org.apache.ibatis.annotations.Mapper;
import space.devincoopers.portfolio.model.Project;
import java.util.List;

@Mapper
public interface PortfolioMapper {
    List<Project> getAllProjects();
}
