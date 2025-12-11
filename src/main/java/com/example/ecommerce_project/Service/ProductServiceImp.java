package com.example.ecommerce_project.Service;

import com.example.ecommerce_project.Dao.ProductDao;
import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.modle.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public Integer CreateProduct(ProductRequest product) {
        return productDao.CreateProduct(product);
    }

    @Override
    public void updateProduct(Integer product_id, Map<String, Object> map) {
        productDao.updateProduct(product_id, map);
    }

    @Override
    public void deleteProduct(Integer product_id) {
        productDao.deleteProduct(product_id);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productDao.getProductsByCategory(category);
    }
}
