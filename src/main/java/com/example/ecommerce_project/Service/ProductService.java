package com.example.ecommerce_project.Service;

import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.modle.Product;

public interface ProductService {
    Product getProductById(int id);
    Integer CreateProduct(ProductRequest product);
}
