package com.example.ecommerce_project.Controller;

import com.example.ecommerce_project.Service.Product.ProductService;
import com.example.ecommerce_project.Service.User.UserService;
import com.example.ecommerce_project.dto_DataTransferObject.RequestUser;
import com.example.ecommerce_project.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/Users/create/account")
    public ResponseEntity<User> create_account(@RequestBody @Valid RequestUser requestUser) {
       return ResponseEntity.status(HttpStatus.OK)
               .body(userService.getUserById(userService.CreateUser(requestUser)));
    }

    //改信箱或密碼需先登入(從Header)
    @PutMapping("/Users/Update/{id}")
    public User updateUser(@PathVariable Integer id
            , @RequestHeader String email
            , @RequestHeader String password
            , @RequestBody @Valid RequestUser requestUser) {
        return userService.updateUser(id, email,password,requestUser);
    }

    @PostMapping("/Users/login")
    public ResponseEntity<User> login(@RequestBody @Valid RequestUser requestUser) {
        User user = userService.UserLogin(requestUser);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
