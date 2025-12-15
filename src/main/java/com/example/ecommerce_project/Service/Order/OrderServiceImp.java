package com.example.ecommerce_project.Service.Order;

import com.example.ecommerce_project.Dao.Order.OrderDao;
import com.example.ecommerce_project.Dao.Product.ProductDao;
import com.example.ecommerce_project.dto_DataTransferObject.Order.BuyItem;
import com.example.ecommerce_project.dto_DataTransferObject.Order.CreateOrderRequest;
import com.example.ecommerce_project.modle.*;
import com.example.ecommerce_project.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImp implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    ProductDao productDao;

    @Transactional
    @Override
    public Integer CreateOrder(Integer UserId,CreateOrderRequest createOrderRequest) {
        //計算總花費 , 單筆花費 檢查(扣除)庫存
        Integer TotalAmmount = 0;
        List<Integer> amount = new ArrayList<>();

        for(BuyItem buyitem: createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyitem.getProductId());
            if(product == null){
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND
                        , "商品id為 " + buyitem.getProductId()+" 不存在!");
            }
            int stock = product.getStock();
            if(stock < buyitem.getQuantity()){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST
                        ,"Product Id為 " + buyitem.getProductId() + " 的 "
                        + product.getProduct_name() + " 庫存只有 " + stock + " 個!");
            }
            productDao.updateStock(buyitem.getProductId(), stock- buyitem.getQuantity());
            amount.add(product.getPrice() *  buyitem.getQuantity());
            TotalAmmount += product.getPrice() *  buyitem.getQuantity();
        }

        Integer order_id = orderDao.CreateOrder(UserId, TotalAmmount);
        orderDao.CreateOrderItem(order_id, createOrderRequest, amount);

        return order_id;
    }

    @Override
    public Order GetOrderByOrderId(Integer OrderId) {
        Order order = orderDao.GetOrderByOrderId(OrderId);
        order.setOrder_item_respond(orderDao.GetOrderItemRespondByOrderId(OrderId));
        return order;
    }


    @Override
    public Page<Order> GetOrderByUserId(Integer UserId, Integer limit, Integer offset) {
        Page<Order> page = new Page<Order>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(orderDao.CountTotalOrder(UserId));
        List<Order> getOrder = orderDao.GetOrderByUserId(UserId,limit,offset);
        for (Order order : getOrder) {
            List<OrderItemRespond> orderItemRespond = orderDao.GetOrderItemRespondByOrderId(order.getOrder_id());
            order.setOrder_item_respond(orderItemRespond);
        }
        page.setResult(getOrder);
        return page;
    }


}
