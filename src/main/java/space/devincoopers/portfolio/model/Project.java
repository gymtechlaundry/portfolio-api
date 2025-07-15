package space.devincoopers.portfolio.model;

import lombok.Data;

import java.util.List;

@Data
public class Project {
    private Long id;
    private String name;
    private String icon;
    private String description;
    private String androidLink;
    private String iosLink;
    private String website;
    private String github;
    private List<String> screenshots;
}
