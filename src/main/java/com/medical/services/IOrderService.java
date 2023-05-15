package com.medical.services;

import com.medical.entity.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getListOrders();
    Order createOrder(Order order);
    List<Order> getOrdersByUserId(Integer userId);

    Order updateOrderAmount(Integer amount , Order order);



}
