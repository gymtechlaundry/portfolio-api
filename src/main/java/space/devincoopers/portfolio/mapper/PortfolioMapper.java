package space.devincoopers.portfolio.mapper;

import org.apache.ibatis.annotations.*;
import space.devincoopers.portfolio.model.Project;
import java.util.List;

@Mapper
public interface PortfolioMapper {
    @Select("SELECT * FROM project")
    @Results(id = "ProjectResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "description", column = "description"),
            @Result(property = "androidLink", column = "android_link"),
            @Result(property = "iosLink", column = "ios_link"),
            @Result(property = "website", column = "website"),
            @Result(property = "github", column = "github"),
            @Result(property = "screenshots", column = "screenshots", typeHandler = space.devincoopers.portfolio.util.ScreenshotListHandler.class)
    })
    List<Project> getAllProjects();

    @Select("SELECT * FROM project WHERE id = #{id}")
    @ResultMap("ProjectResultMap")
    Project getProjectById(Long id);

    @Update("UPDATE project set " +
    "name = #{name}. " +
    "icon = #{icon} " +
    "description = #{description} " +
    "android_link = #{androidLink} " +
    "ios_link = #{iosLink} " +
    "website = #{website} " +
    "github = #{github} " +
    "screenshots = #{screenshots, typeHandler=space.devincoopers.portfolio.util.ScreenshotListHandler} " +
    "WHERE id = #{id}")
    void updateProject(Project project);

    @Insert("INSERT INTO project (name, icon, description, android_link, ios_link, website, github, screenshots) " +
            "VALUES (#{name}, #{icon}, #{description}, #{androidLink}, #{iosLink}, #{website}, #{github}, #{screenshots, typeHandler=space.devincoopers.portfolio.util.ScreenshotListHandler})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertProject(Project project);

    @Delete("DELETE FROM project WHERE id = #{id}")
    void deleteProjectById(Long id);
}
