package com.medical.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "`rating`" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_Id",nullable = false)
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_Id",nullable = false)
    private Product product;


    @Column(name = "`comment`",length = 1000, nullable = false)
    private String comment;

    @Column(name = "created_At")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "update_At")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    public Rating(User user, Product product, String comment) {
        this.user = user;
        this.product = product;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
