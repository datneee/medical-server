package com.medical.repositories;

import com.medical.entity.ResetPasswordUserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResetPasswordUserTokenRepository extends JpaRepository<ResetPasswordUserToken, Integer> {
    void deleteByUserId(Integer userId);
}
