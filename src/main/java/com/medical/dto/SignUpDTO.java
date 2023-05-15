package com.medical.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    @NotBlank
    private String username;

    @NotBlank
//    @Email
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&_]{8,255}$", message = "is invalid")
    private String password;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String address;

}
