package com.medical.repositories;

import com.medical.constants.StatusOrderItem;
import com.medical.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderItemRepository extends JpaRepository<OrderItem,Integer> {

    @Query("select u from OrderItem u where u.status = ?1")
    List<OrderItem> findOrderItemsByStatusIs(StatusOrderItem statusOrderItem);

    @Query("select u from OrderItem u where u.status = 'Complete' and MONTH(u.createdDate) = ?1")
    List<OrderItem> findOrderItemsByReceivedDateAndStatus(int month);

    @Query("select u from OrderItem u where MONTH(u.createdDate) = ?1")
    List<OrderItem> getMonthlyOrder(int month);
    OrderItem findOrderItemById(Integer id);

    void deleteById(Integer id);

}
