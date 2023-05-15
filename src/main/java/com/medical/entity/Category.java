package com.medical.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medical.constants.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`name`",length = 255,nullable = false)
    private String name;

    @Column(name = "descriptions",length = 500)
    private String descriptions;

    @Column(name = "`status`")
    private StatusCodeEnum status;

    @OneToMany(mappedBy = "category")
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CategoryImage> categoryImages;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

    @PrePersist
    public void PrePersist(){
        if(this.status == null)
            this.status = StatusCodeEnum.ACTIVE;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", categoryImages=" + categoryImages +
                ", products=" + products +
                '}';
    }
}
