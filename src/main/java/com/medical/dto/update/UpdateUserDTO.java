package com.medical.dto.update;

import com.medical.validators.NullOrNotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class UpdateUserDTO {
    @NullOrNotEmpty(message = "is invalid")
    @Length(min = 10)
    private String username;

    @NullOrNotEmpty(message = "is invalid")
    @Length(min = 10)
    private String address;


    @NullOrNotEmpty(message = "is invalid")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&_]{8,255}$", message = "is invalid")
    private String password;

    @NullOrNotEmpty(message = "is invalid")
    @Length(max = 15)
    private String phone;


    @NullOrNotEmpty(message = "is invalid")
    @Length(min = 10)
    private String email;

    @NullOrNotEmpty(message = "is invalid")
    @Length(min = 10)
    private String fullName;

    private String role;

    private String status;


}
