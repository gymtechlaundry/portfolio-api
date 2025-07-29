package space.devincoopers.portfolio.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    List<String> roles;
}
