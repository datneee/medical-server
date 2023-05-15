package com.medical.services;

import org.springframework.security.core.Authentication;

public interface IUserAuthorizerService {
    boolean isAdmin(Authentication authentication);

    boolean isClient(Authentication authentication);

    boolean isYourself(Authentication authentication, Integer userId);
}
