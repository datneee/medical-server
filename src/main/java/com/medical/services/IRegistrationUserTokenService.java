package com.medical.services;

import com.medical.entity.User;

public interface IRegistrationUserTokenService {
    void createNewRegistrationUserToken(User user, final String jwt);
}
