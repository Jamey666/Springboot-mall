package com.example.ecommerce_project.dto_DataTransferObject;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BCryptBean {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

}
