package com.example.ecommerce_project.Dao.User;

import com.example.ecommerce_project.dto_DataTransferObject.RequestUser;
import com.example.ecommerce_project.mapper.UserMapper;
import com.example.ecommerce_project.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImp implements UserDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer CreateUser(RequestUser requestUser) {
        String sql = "INSERT INTO user (email, password, created_date, last_modified_date) values ( :email, :password, NOW(), NOW());";
        MapSqlParameterSource map =  new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        map.addValue("email", requestUser.getEmail());
        map.addValue("password", requestUser.getPassword());
        namedParameterJdbcTemplate.update(sql,map,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public User getUserById(Integer id) {
        String sql = "SELECT * FROM user WHERE user_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        List<User> get_user = namedParameterJdbcTemplate.query(sql,map,new UserMapper());
        if(get_user.size()>0){
            return get_user.get(0);
        }else {
            return null;
        }
    }

    @Override
    public User updateUser(Integer id,RequestUser requestUser) {
        String sql = "update user set email = :email, password = :password where user_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", requestUser.getEmail());
        map.addValue("password", requestUser.getPassword());
        map.addValue("id", id);
        namedParameterJdbcTemplate.update(sql,map);
        return this.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = :email";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("email", email);
        List<User> get_user = namedParameterJdbcTemplate.query(sql,map,new UserMapper());
        if(get_user.size()>0){
            return get_user.get(0);
        }else  {
            return null;
        }
    }
}
