package com.example.ecommerce_project.Dao;

import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.modle.Product;
import com.example.ecommerce_project.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    @Override
    public Integer CreateProduct(ProductRequest product) {    //傳入Product建立後返回剛剛建立的Product
        String sql = "INSERT INTO product (product_name, category, image_url, price," +
                " stock, description, created_date, last_modified_date) " +
                "VALUES (:product_name, :category, :image_url, :price," +
                " :stock, :description, Now(), Now());";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("product_name", product.getProduct_name());
        map.addValue("category", product.getCategory().name());
        map.addValue("image_url", product.getImage_url());
        map.addValue("price",product.getPrice());
        map.addValue("stock",product.getStock());
        map.addValue("description",product.getDescription());
//        map.addValue("created_date", product.getCreated_date());
//        map.addValue("last_modified_date", product.getLast_modified_date());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,map,keyHolder);
        return keyHolder.getKey().intValue();
    }
}
