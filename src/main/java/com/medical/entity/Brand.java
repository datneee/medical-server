package com.medical.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
public class Brand implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`name`",length = 255,nullable = false)
    private String name;

    @Column(name = "`webPage`",length = 500)
    private String webpage;

    @Column(name = "logo", length = 500)
    private String logo;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private List<Product>  products;
}
