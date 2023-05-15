package com.medical.models;

import com.medical.dto.CustomerDTO;
import com.medical.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private String token;
    private CustomerDTO user;
}