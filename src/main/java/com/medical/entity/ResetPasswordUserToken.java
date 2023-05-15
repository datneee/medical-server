package com.medical.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ResetPasswordUserToken")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordUserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    @Override
    public String toString() {
        return "ResetPasswordUserToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }

    public ResetPasswordUserToken(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
