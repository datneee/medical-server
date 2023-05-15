package com.medical.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name = "CategoryImages")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryImage implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image_Url",nullable = false)
    private String imageUrl;


    @Column(name = "imagePublicId" , length = 500)
    private String imagePublicId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "category_id")
    private Category category;


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
        return "CategoryImage{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", imagePublicId='" + imagePublicId + '\'' +
                ", category=" + category +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public CategoryImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
