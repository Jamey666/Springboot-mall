package com.example.ecommerce_project.Service.User;

import com.example.ecommerce_project.dto_DataTransferObject.RequestUser;
import com.example.ecommerce_project.modle.User;

public interface UserService {
    Integer CreateUser(RequestUser requestUser);
    User getUserById(Integer id);
    User updateUser(Integer id,String email,String password,RequestUser requestUser);
    User getUserByEmail(String email);
    User UserLogin(RequestUser requestUser);
}
