package com.medical.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_Id",nullable = false)
    @JsonManagedReference
    private User user;

    @Column(name = "amount")
    private Integer amount;


    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItemList;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", amount=" + amount +
                ", cartItemList=" + cartItemList +
                '}';
    }

    @PrePersist
    public void PrePersist(){
        this.amount = this.cartItemList.size();
    }


}
