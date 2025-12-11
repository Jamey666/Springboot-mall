package com.example.ecommerce_project.Dao;

import com.example.ecommerce_project.Constant.ProductCategory;
import com.example.ecommerce_project.dto_DataTransferObject.ProductRequest;
import com.example.ecommerce_project.dto_DataTransferObject.RequestParameter;
import com.example.ecommerce_project.modle.Product;
import com.example.ecommerce_project.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImp implements ProductDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    @Override
    public Product getProductById(int id) {
        String sql = "select * from product where product_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        List<Product> get_product = namedParameterJdbcTemplate.query(sql,map,new ProductMapper());
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

    @Override
    public void updateProduct(Integer product_id, Map<String,Object> map) {
        StringBuilder sb = new StringBuilder();
        MapSqlParameterSource param_map = new MapSqlParameterSource();
        sb.append("UPDATE product SET ");
        int i = 0;
        for(Map.Entry<String,Object> entry : map.entrySet()){
            if(entry.getKey().equals("product_id") || entry.getKey().equals("created_date") || entry.getKey().equals("last_modified_date")){
                throw new RuntimeException("created_date last_modified_date product_id 這三項是不能修改的歐~");
            } // created_date last_modified_date product_id 不能改
            if(entry.getKey().equals("category")){  //　如果要修改category 先檢查是否為有效值
                ProductCategory.valueOf((String) entry.getValue()); //如果轉不過去就在這裡error
            }
            if(i!=0){
                sb.append(",");
            }
            sb.append(entry.getKey()).append("=:").append(entry.getKey());
            param_map.addValue(entry.getKey(),entry.getValue());
            i++;
        }
        sb.append(" ,last_modified_date = Now() WHERE product_id = :product_id");
        param_map.addValue("product_id",product_id);
        String sql = sb.toString();
        namedParameterJdbcTemplate.update(sql,param_map);
    }

    @Override
    public void deleteProduct(Integer product_id) {
        String sql = "delete from product where product_id = :product_id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("product_id",product_id);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public List<Product> getProducts(RequestParameter requestParameter) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from product where 1=1 ");
        if(requestParameter.getCategory()!=null){
            sb.append("and category = :category");
            map.addValue("category",requestParameter.getCategory().name());
        }
        if(requestParameter.getName()!=null){
            sb.append(" and product_name like :name");
            map.addValue("name","%"+requestParameter.getName()+"%");
        }
        sb.append(" order by " + requestParameter.getOrder()+" "+requestParameter.getSort());
        sb.append(" limit :limit");
        map.addValue("limit",requestParameter.getLimit());
        sb.append(" OFFSET :offset");
        map.addValue("offset",requestParameter.getOffset());
        String sql = sb.toString();
        List<Product> product_list = namedParameterJdbcTemplate.query(sql,map,new ProductMapper());
        return product_list;
    }// ex: select * from  product where product_name like '%B%' and category='CAR' order by price desc limit 2 offset 2;

    @Override
    public Integer countProducts(RequestParameter requestParameter) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from product where 1=1 ");
        if(requestParameter.getCategory()!=null){
            sb.append("and category = :category");
            map.addValue("category",requestParameter.getCategory().name());
        }
        if(requestParameter.getName()!=null){
            sb.append(" and product_name like :name");
            map.addValue("name","%"+requestParameter.getName()+"%");
        }
        String sql = sb.toString();
        return namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);
    }

    //    @Override
//    public List<Product> getProducts() {
//        String sql = "select * from product";
//        MapSqlParameterSource map = new MapSqlParameterSource();
//        List<Product> product_list = namedParameterJdbcTemplate.query(sql,map,new ProductMapper());
//        return product_list;
//    }
//
//    @Override
//    public List<Product> getProductsByCategory(String category) {
//        String sql = "select * from product where category = :category";
//        MapSqlParameterSource map = new MapSqlParameterSource();
//        map.addValue("category", category);
//
//        List<Product> product_list = namedParameterJdbcTemplate.query(sql,map,new ProductMapper());
//        if (product_list.size()>0){
//            return product_list;
//        }else  {
//            return null;
//        }
//    }




}
