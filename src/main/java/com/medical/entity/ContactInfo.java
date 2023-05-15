package com.medical.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ContactInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email" , nullable = false, unique = true)
    private String email;

    @Column(name = "`name`")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "comments")
    private String comments;

    public ContactInfo(String email, String name, String phoneNumber, String comments) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.comments = comments;
    }
}
