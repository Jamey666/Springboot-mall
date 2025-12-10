package com.example.ecommerce_project.Service;

import com.example.ecommerce_project.Dao.ProductDao;
import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.modle.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
