package com.medical.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ProductImages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImages implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "image_Url",nullable = false)
    private String imageUrl;


    @Column(name = "imagePublicId" , length = 500)
    private String imagePublicId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_Id")
    private Product product;


    @Column(name = "created_At")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp //Deafault now
    private Date createdAt;

    @Column(name = "updated_At")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp //Deafault now
    private Date updatedAt;

    @Override
    public String toString() {
        return "ProductImages{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", imagePublicId='" + imagePublicId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public ProductImages(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
