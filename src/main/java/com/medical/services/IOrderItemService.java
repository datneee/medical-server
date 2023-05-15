package com.medical.services;

import com.medical.constants.StatusOrderItem;
import com.medical.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {

    List<OrderItem> getAllOrderItemsByStatus(StatusOrderItem status);
    List<OrderItem> getMonthlyOrder(int month);

    OrderItem getOrderItemById(Integer id);

    Integer getMonthlyRevenue(int month);



    void createOrderItems(OrderItem orderItem);

    void updateOrderItemStatus(Integer id , StatusOrderItem status);

    void deleteById(Integer id);

}
