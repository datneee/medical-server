package com.medical.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "shipFees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipFees implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`voucher`")
    private String voucher;

    @Column(name = "fee")
    private int fee;

    public ShipFees(String voucher, int fee) {
        this.voucher = voucher;
        this.fee = fee;
    }
}
