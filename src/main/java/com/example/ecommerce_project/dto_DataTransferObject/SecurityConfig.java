package com.example.ecommerce_project.dto_DataTransferObject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()       // 🔥 舊版本用這個
                .anyRequest().permitAll()  // 全部放行
                .and()
                .formLogin().disable()     // 不要自動跳出登入畫面
                .httpBasic().disable();    // 不要彈出 basic auth
        return http.build();
    }
}