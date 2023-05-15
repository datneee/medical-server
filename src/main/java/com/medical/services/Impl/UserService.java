package com.medical.services.Impl;

import com.medical.base.BasePagination;
import com.medical.constants.StatusCodeEnum;
import com.medical.dto.CustomerDTO;
import com.medical.dto.SignUpDTO;
import com.medical.dto.pagination.PaginateDTO;
import com.medical.dto.update.UpdateUserDTO;
import com.medical.entity.AuthenUserToken;
import com.medical.entity.User;
import com.medical.repositories.IRegistrationUserTokenRepository;
import com.medical.repositories.IUserRepository;
import com.medical.services.IUserService;
import com.medical.specifications.GenericSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BasePagination<User, IUserRepository> implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRegistrationUserTokenRepository registrationUserTokenRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository){
        super(userRepository);
    }

    @Override
    public PaginateDTO<User> getList(Integer page, Integer perPage, GenericSpecification<User> specification) {
        return this.paginate(page, perPage, specification);
    }

    @Override
    public CustomerDTO findByEmail(String email) {

        User user = userRepository.findByEmail(email);
        CustomerDTO dto = modelMapper.map(user, CustomerDTO.class);
        return dto;
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public CustomerDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        CustomerDTO dto = modelMapper.map(user, CustomerDTO.class);
        return dto;
    }

    @Override
    public User create(SignUpDTO signUpDTO) {
        User user = modelMapper.map(signUpDTO, User.class);
        return userRepository.save(user);
    }

    @Override
    public void activeUser(String token) {
        AuthenUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);
        User user = registrationUserToken.getUser();
        user.setStatus(StatusCodeEnum.ACTIVE);

        userRepository.save(user);

        registrationUserTokenRepository.deleteById(registrationUserToken.getId());
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User update(UpdateUserDTO userUpdateDTO, User currentUser) {
        User updated = modelMapper.map(userUpdateDTO, User.class);
        modelMapper.map(updated, currentUser);
        if(userUpdateDTO.getPassword() != null)
            currentUser.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        currentUser.setStatus(StatusCodeEnum.valueOf(userUpdateDTO.getStatus()));
        currentUser.setRole(userUpdateDTO.getRole());
        return userRepository.save(currentUser);
    }

    @Override
    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

}
