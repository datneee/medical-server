package com.medical.services;

import com.medical.entity.ResetPasswordUserToken;

public interface IResetPasswordUserTokenService {
    void deleteResetPasswordTokenByUserId(Integer userId);

    void createResetPasswordUserToken(ResetPasswordUserToken resetPasswordUserToken);

    void deleteById(Integer id);
}
