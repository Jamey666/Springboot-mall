package com.example.ecommerce_project.Service.Product;

import com.example.ecommerce_project.Dao.Product.ProductDao;
import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.dto_DataTransferObject.RequestParameter;
import com.example.ecommerce_project.modle.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.net.HttpRetryException;
import java.util.List;
import java.util.Map;

@Component
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(int id) {
        Product getProduct = productDao.getProductById(id);
        if(getProduct == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"不存在id為" + id  + "此帳號");
        }else {
            return getProduct;
        }

    }

    @Override
    public Integer CreateProduct(ProductRequest product) {
        return productDao.CreateProduct(product);
    }

    @Override
    public void updateProduct(Integer product_id, Map<String, Object> map) {
        if(productDao.getProductById(product_id) == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"id為" + product_id + "帳號不存在");
        }
        productDao.updateProduct(product_id, map);
    }

    @Override
    public void deleteProduct(Integer product_id) {
        productDao.deleteProduct(product_id);
    }

    @Override
    public List<Product> getProducts(RequestParameter requestParameter) {
        return productDao.getProducts(requestParameter);
    }

    @Override
    public Integer countProducts(RequestParameter requestParameter) {
        return productDao.countProducts(requestParameter);
    }




}
