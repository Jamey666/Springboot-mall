package com.example.ecommerce_project.Controller;


import com.example.ecommerce_project.Constant.ProductCategory;
import com.example.ecommerce_project.Service.ProductService;
import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.modle.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        Product get_product = productService.getProductById(id);
        if (get_product  == null) {
            return ResponseEntity.notFound().build();
        }else  {
            return ResponseEntity.ok().body(get_product);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<?> postOne(@Valid @RequestBody ProductRequest product) {
        Integer product_id = productService.CreateProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.getProductById(product_id));
    }

    @PutMapping("/update/product/{id}")
    public ResponseEntity<?> tes(@PathVariable Integer id,@RequestBody Map<String,Object> map){

        Product get_product = productService.getProductById(id);
        if (get_product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("此id為 " + id + " 之商品不存在!");
        }

        productService.updateProduct(id, map);
        return ResponseEntity.ok().body(productService.getProductById(id));
    }



}
