package com.example.ecommerce_project.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handle_Exception {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidEnum(HttpMessageNotReadableException ex) {
        String message = "請輸入正確的 Category 值，例如：E_BOOK, CAR, FOOD";
        return ResponseEntity.badRequest().body(message);
    }

}
