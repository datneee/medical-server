package com.medical.services;

import com.medical.dto.CustomerDTO;
import com.medical.dto.SignUpDTO;
import com.medical.dto.pagination.PaginateDTO;
import com.medical.dto.update.UpdateUserDTO;
import com.medical.entity.User;
import com.medical.specifications.GenericSpecification;

public interface IUserService  {
    PaginateDTO<User> getList(Integer page, Integer perPage, GenericSpecification<User> specification);

    CustomerDTO findByEmail(String email);
    User getUserByEmail(String email);
    CustomerDTO findByUsername(String username);
    User getUserByUsername(String username);

    User create(SignUpDTO signUpDTO);

    void activeUser(String token);

    User updateUser(User user);

    void create(User user);

    User findById(Integer userId);

    User update(UpdateUserDTO updateUserDTO, User currentUser);

    void deleteById(Integer userId);
}
