package com.medical.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_Id",nullable = false)
    private User user;


    @Column(name = "amount")
    private Integer amount;

    @Column(name = "shipment")
    private String shipment;

    @Column(name = "shipAddress")
    private String shipAddress;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order(User user, Integer amount) {
        this.amount = amount;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", orderItems=" + orderItems +
                '}';
    }
}
