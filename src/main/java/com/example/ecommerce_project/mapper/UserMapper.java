package com.example.ecommerce_project.mapper;


import com.example.ecommerce_project.modle.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUser_id(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setCreated_date((resultSet.getDate("created_date")));
        user.setLast_modified_date((resultSet.getDate("last_modified_date")));
        return user;
    }
}
