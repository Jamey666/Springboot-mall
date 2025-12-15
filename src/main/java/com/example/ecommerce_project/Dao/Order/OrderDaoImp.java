package com.example.ecommerce_project.Dao.Order;

import com.example.ecommerce_project.Dao.Product.ProductDao;
import com.example.ecommerce_project.dto_DataTransferObject.Order.CreateOrderRequest;
import com.example.ecommerce_project.mapper.OrderItemRespondMapper;
import com.example.ecommerce_project.mapper.OrderMapper;
import com.example.ecommerce_project.modle.Order;

import com.example.ecommerce_project.modle.OrderItemRespond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class OrderDaoImp implements OrderDao{

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    ProductDao productDao;

    @Override
    public Integer CreateOrder(Integer UserId, Integer TotalAmount) {
        String sql = "INSERT INTO `order` (user_id, total_amount , created_date , last_modified_date) VALUES ( :user_id, :total_amount , Now() , Now())";
        MapSqlParameterSource map =  new MapSqlParameterSource();
        map.addValue("user_id",UserId);
        map.addValue("total_amount",TotalAmount);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,map,keyHolder);
        Integer orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void CreateOrderItem(Integer OrderId, CreateOrderRequest createOrderRequest, List<Integer> amount) {
        String sql = "insert into order_item(order_id, product_id, quantity , amount) " +
                "values (:order_id, :product_id, :quantity , :amount)";
        MapSqlParameterSource[] map = new MapSqlParameterSource[amount.size()];
        for (int i = 0; i < map.length; i++) {
            map[i] = new MapSqlParameterSource();
            map[i].addValue("order_id",OrderId);
            map[i].addValue("product_id",createOrderRequest.getBuyItemList().get(i).getProductId());
            map[i].addValue("quantity",createOrderRequest.getBuyItemList().get(i).getQuantity());
            map[i].addValue("amount",amount.get(i));
        }
        namedParameterJdbcTemplate.batchUpdate(sql,map);
    }

    @Override
    public Order GetOrderByOrderId(Integer OrderId) {
        String sql = "select * from `order` where order_id = :order_id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("order_id",OrderId);
        List<Order> GetOrder = namedParameterJdbcTemplate.query(sql,map,new OrderMapper());
        if(GetOrder.size()>0){
            return GetOrder.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<Order> GetOrderByUserId(Integer UserId, Integer limit, Integer offset) {
        String sql = "select * from `order` where user_id = :user_id order by created_date desc limit :limit offset :offset";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user_id",UserId);
        map.addValue("limit",limit);
        map.addValue("offset",offset);
        List<Order> GetOrder = namedParameterJdbcTemplate.query(sql,map,new OrderMapper());
        if(GetOrder.size()>0){
            return GetOrder;
        }else  {
            return null;
        }
    }

    @Override
    public List<OrderItemRespond> GetOrderItemRespondByOrderId(Integer OrderId) {
        String sql ="SELECT p.product_name,p.image_url,p.price,oi.quantity,oi.amount  " +
                "FROM order_item AS oi LEFT JOIN product AS p ON oi.product_id=p.product_id  " +
                "where oi.order_id = :order_id";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("order_id",OrderId);
        List<OrderItemRespond> GetOrderItemRespond = namedParameterJdbcTemplate.query(sql,map,new OrderItemRespondMapper());
        if(GetOrderItemRespond.size()>0){
            return GetOrderItemRespond;
        }else  {
            return null;
        }
    }

    @Override
    public Integer CountTotalOrder(Integer UserId) {
        String sql = "select count(*) from `order` where user_id = :user_id ";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user_id",UserId);
        return namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);
    }
}

