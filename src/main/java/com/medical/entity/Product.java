package com.medical.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medical.constants.IsHotProductEnum;
import com.medical.constants.StatusCodeProductEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`title`",length = 255,nullable = false)
    private String title;

    @Column(name = "`descriptions`",length = 1000,nullable = false)
    private String descriptions;

    @Column(name = "originalPrice",nullable = false)
    private Integer originalPrice;

    @Column(name = "promotionPrice",nullable = false)
    private Integer promotionPrice;

    @Column(name = "`created_Date`")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdDate;
    @Column(name = "currentAmount",nullable = false)
    private Integer currentAmount;
    @Column(name = "amount",nullable = false)
    private Integer amount;

    @Column(name = "`isHot`", columnDefinition = "0")
    private IsHotProductEnum isHot;
    @Column(name = "`status`" , columnDefinition = "1")
    private StatusCodeProductEnum status;

    @OneToOne
    @JoinColumn(name = "ticketId")
    private Ticket ticket;

    public Product(String title, String descriptions, int originalPrice, int promotionPrice,Integer currentAmount, Integer amount) {
        this.title = title;
        this.descriptions = descriptions;
        this.originalPrice = originalPrice;
        this.promotionPrice = promotionPrice;
        this.currentAmount = currentAmount;
        this.amount = amount;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "categoryId",nullable = false)
    private Category category;



    @ManyToOne
    @JoinColumn(name = "brandId", nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "product")
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<ProductImages> productImages;

    @OneToMany(mappedBy = "product")
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Rating> productRatesList;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CartItem> cartItemList;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<OrderItem> orderItems;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", originalPrice=" + originalPrice +
                ", promotionPrice=" + promotionPrice +
                ", createdDate=" + createdDate +
                ", currentAmount=" + currentAmount +
                ", amount=" + amount +
                ", isHot=" + isHot +
                ", status=" + status +
                ", productImages=" + productImages +
                ", productRatesList=" + productRatesList +
                '}';
    }

    @PrePersist
    public void PrePersist(){
        if (this.ticket != null) this.promotionPrice = this.originalPrice * this.ticket.getDiscount() / 100;
        if(this.status == null)
            this.status = StatusCodeProductEnum.OPENING;
        if(this.promotionPrice == null)
            this.promotionPrice = originalPrice;
    }

}
