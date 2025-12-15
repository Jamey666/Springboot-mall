package com.example.ecommerce_project.Dao.User;

import com.example.ecommerce_project.dto_DataTransferObject.RequestUser;
import com.example.ecommerce_project.modle.User;

public interface UserDao {
    Integer CreateUser(RequestUser requestUser);
    User getUserById(Integer id);
    User updateUser(Integer id,RequestUser requestUser);
    User getUserByEmail(String email);



}
