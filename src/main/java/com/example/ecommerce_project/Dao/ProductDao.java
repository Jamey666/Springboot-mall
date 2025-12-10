package com.example.ecommerce_project.Dao;

import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.modle.Product;

public interface ProductDao {
    Product getProductById(int id);
    Integer CreateProduct(ProductRequest product);
}
