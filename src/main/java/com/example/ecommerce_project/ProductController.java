package com.example.ecommerce_project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @GetMapping("/test/get")
    public Product get(@RequestParam Integer id) {
        String sql = "select * from product where product_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql,map,new ProductMapper());
    }
}
