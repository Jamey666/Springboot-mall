package com.example.ecommerce_project.Controller;

import com.example.ecommerce_project.Service.Order.OrderService;
import com.example.ecommerce_project.Service.User.UserService;
import com.example.ecommerce_project.dto_DataTransferObject.Order.CreateOrderRequest;
import com.example.ecommerce_project.modle.Order;
import com.example.ecommerce_project.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;

    @PostMapping("/users/{user_id}/createorders")
    public ResponseEntity<?> CreateOrder(@PathVariable Integer user_id
            , @RequestBody @Valid CreateOrderRequest createOrderRequest) {
        System.out.println(createOrderRequest.getBuyItemList());
        if(userService.getUserById(user_id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        Integer order_id = orderService.CreateOrder(user_id,createOrderRequest);
        Order order = orderService.GetOrderByOrderId(order_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


    @GetMapping("/user/{id}/orders")
    public Page<Order> haha(@PathVariable Integer id
            , @RequestParam(defaultValue = "1") @Min (value = 1)  Integer limit
            , @RequestParam(defaultValue = "0") @Min(0)  Integer offset) {
        return orderService.GetOrderByUserId(id,limit,offset);
    }

}
