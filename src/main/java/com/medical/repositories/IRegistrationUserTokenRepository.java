package com.medical.repositories;

import com.medical.entity.AuthenUserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegistrationUserTokenRepository extends JpaRepository<AuthenUserToken, Integer> {

    AuthenUserToken findByToken(String token);
}
