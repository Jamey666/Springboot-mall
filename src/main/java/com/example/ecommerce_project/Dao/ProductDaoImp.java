package com.example.ecommerce_project.Dao;

import com.example.ecommerce_project.Product;
import com.example.ecommerce_project.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImp implements ProductDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(int id) {
        String sql = "select * from product where product_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        List<Product> get_product =  namedParameterJdbcTemplate.query(sql,map,new ProductMapper());
        if(get_product.size()>0){
            return get_product.get(0);
        }else {
            return null;
        }
    }
}
