package com.medical.services.Impl;


import com.medical.entity.Order;
import com.medical.repositories.IOrderRepository;
import com.medical.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository repository;


    @Override
    public List<Order> getListOrders() {
        return repository.findAll();
    }

    @Override
    public Order createOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        return repository.getAllByUserId(userId);
    }

    @Override
    public Order updateOrderAmount(Integer amount, Order order) {
        order.setAmount(amount);
        repository.save(order);
        return order;
    }


}
