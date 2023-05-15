package com.medical.services.Impl;

import com.medical.constants.RoleEnum;
import com.medical.dto.CustomerDTO;
import com.medical.services.IUserAuthorizerService;
import com.medical.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service("userAuthorizer")
public class UserAuthorizerService implements IUserAuthorizerService {
    @Autowired
    private IUserService userService;

    @Override
    public boolean isAdmin(Authentication authentication) {
        return Arrays.toString(authentication.getAuthorities().toArray()).contains(RoleEnum.ADMIN);
    }

    @Override
    public boolean isClient(Authentication authentication) {
        return Arrays.toString(authentication.getAuthorities().toArray()).contains(RoleEnum.CLIENT);
    }

    @Override
    public boolean isYourself(Authentication authentication, Integer userId) {
        User userAuth = (User) authentication.getPrincipal();
        CustomerDTO user = userService.findByUsername(userAuth.getUsername());
        if (!Objects.equals(user.getId(), userId)) {
            throw new AccessDeniedException("Access denied");
        }
        return true;
    }
}
