package com.example.ecommerce_project.Service.Order;

import com.example.ecommerce_project.dto_DataTransferObject.Order.CreateOrderRequest;
import com.example.ecommerce_project.modle.Order;
import com.example.ecommerce_project.util.Page;


public interface OrderService {
    Integer CreateOrder(Integer UserId,CreateOrderRequest createOrderRequest);
    Order GetOrderByOrderId(Integer OrderId);

    Page<Order> GetOrderByUserId(Integer UserId, Integer limit, Integer offset);
}
