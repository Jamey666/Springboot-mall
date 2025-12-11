package com.example.ecommerce_project.Dao;

import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.modle.Product;

import java.util.List;
import java.util.Map;

public interface ProductDao {
    Product getProductById(int id);
    List<Product> getProducts();
    List<Product> getProductsByCategory(String category);

    Integer CreateProduct(ProductRequest product);

    void updateProduct(Integer product_id, Map<String,Object> map);

    void deleteProduct(Integer product_id);
}
