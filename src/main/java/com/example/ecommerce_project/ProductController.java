package com.example.ecommerce_project;


import com.example.ecommerce_project.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/test/get/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        Product get_product = productService.getProductById(id);
        if (get_product  == null) {
            return ResponseEntity.notFound().build();
        }else  {
            return ResponseEntity.ok().body(get_product);
        }
    }
}
