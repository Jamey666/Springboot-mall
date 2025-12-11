package com.example.ecommerce_project.ExceptionHandle;


import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class Handle_Exception {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidEnum(HttpMessageNotReadableException ex) {
        String message = "請輸入正確的 Category 值，例如：E_BOOK, CAR, FOOD";
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException rx) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rx.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleEnumError(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("category 不合法，請傳入正確的商品分類");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleEnumError(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.badRequest().body("category 不合法，請傳入正確的商品分類");
    }

}
