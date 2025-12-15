package com.example.ecommerce_project.Dao.Order;

import com.example.ecommerce_project.dto_DataTransferObject.Order.CreateOrderRequest;
import com.example.ecommerce_project.modle.Order;
import com.example.ecommerce_project.modle.OrderItem;
import com.example.ecommerce_project.modle.OrderItemRespond;

import java.util.List;

public interface OrderDao {
    Integer CreateOrder(Integer UserId,Integer TotalAmount);
    void CreateOrderItem(Integer OrderId,CreateOrderRequest createOrderRequest, List<Integer> amount);

    Order GetOrderByOrderId(Integer OrderId);
    List<OrderItemRespond> GetOrderItemRespondByOrderId(Integer OrderId);

    List<Order> GetOrderByUserId(Integer UserId ,Integer limit,Integer offset);
    Integer CountTotalOrder(Integer UserId);

}
