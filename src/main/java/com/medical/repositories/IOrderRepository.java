package com.medical.repositories;

import com.medical.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Integer> {

    List<Order> getAllByUserId(Integer id);
    Order findOrderByUserId(Integer id);



}
