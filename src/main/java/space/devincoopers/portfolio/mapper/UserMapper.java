package space.devincoopers.portfolio.mapper;

import org.apache.ibatis.annotations.*;
import space.devincoopers.portfolio.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "roles", column = "id",
                many = @Many(select = "getRolesByUserId"))
    })
    User findByUsername(String username);

    @Select("SELECT r.name FROM roles r " +
            "JOIN user_roles ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{user_id}")
    List<String> getRolesByUserId(Long userId);
}
