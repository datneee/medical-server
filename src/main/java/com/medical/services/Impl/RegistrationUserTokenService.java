package com.medical.services.Impl;

import com.medical.entity.AuthenUserToken;
import com.medical.entity.User;
import com.medical.repositories.IRegistrationUserTokenRepository;
import com.medical.services.IRegistrationUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationUserTokenService implements IRegistrationUserTokenService {
    @Autowired
    private IRegistrationUserTokenRepository registrationUserTokenRepository;

    @Override
    public void createNewRegistrationUserToken(User user, final String jwt) {

        AuthenUserToken tokenEntity = new AuthenUserToken(user, jwt);
        registrationUserTokenRepository.save(tokenEntity);
    }
}
