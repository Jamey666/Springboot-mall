package com.example.ecommerce_project.mapper;

import com.example.ecommerce_project.modle.OrderItemRespond;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRespondMapper implements RowMapper<OrderItemRespond> {
    @Override
    public OrderItemRespond mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderItemRespond orderItemRespond = new OrderItemRespond();
        orderItemRespond.setProductName(resultSet.getString("product_name"));
        orderItemRespond.setImageUrl(resultSet.getString("image_url"));
        orderItemRespond.setPrice(resultSet.getInt("price"));
        orderItemRespond.setQuantity(resultSet.getInt("quantity"));
        orderItemRespond.setAmount(resultSet.getInt("amount"));
        return orderItemRespond;
    }
}
