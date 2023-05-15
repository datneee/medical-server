package com.medical.controllers;

import com.medical.base.BaseController;
import com.medical.constants.Common;
import com.medical.dto.UserChangePasswordDTO;
import com.medical.dto.pagination.PaginateDTO;
import com.medical.dto.update.UpdateUserDTO;
import com.medical.entity.User;
import com.medical.exceptions.AppException;
import com.medical.exceptions.NotFoundException;
import com.medical.services.IUserService;
import com.medical.specifications.GenericSpecification;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "Authorization")
@CrossOrigin("*")
public class UserController extends BaseController<User> {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping
    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> getAllUser(@RequestParam(name = "page", required = false) Integer page,
                                        @RequestParam(name = "perPage", required = false) Integer perPage,
                                        HttpServletRequest request){
        GenericSpecification<User> specification = new GenericSpecification<User>().getBasicQuery(request);
        PaginateDTO<User> paginateUsers = userService.getList(page, perPage, specification);
        return this.resPagination(paginateUsers);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("@userAuthorizer.isAdmin(authentication) || @userAuthorizer.isYourself(authentication, #userId)")
    public ResponseEntity<?> getUserById(@PathVariable(name = "userId") Integer userId){
        User user = userService.findById(userId);
        if(user == null)
            throw new NotFoundException(Common.MSG_NOT_FOUND);
        return this.resSuccess(user);
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("@userAuthorizer.isAdmin(authentication) || @userAuthorizer.isYourself(authentication, #userId)")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO userDTO,
                                        @PathVariable("userId") Integer userId){
        User user = userService.findById(userId);
        if(user == null)
            throw new NotFoundException(Common.MSG_NOT_FOUND);

        User savedUser = userService.update(userDTO, user);
        return this.resSuccess(savedUser);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("@userAuthorizer.isAdmin(authentication)")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId){
        User user = userService.findById(userId);
        if (user == null) {
            throw new NotFoundException(Common.MSG_NOT_FOUND);
        }



        userService.deleteById(userId);

        return new ResponseEntity<>(Common.MSG_DELETE_SUCCESS, HttpStatus.OK);
    }

//    @PatchMapping("/password")
//    public ResponseEntity<?> changePassword(@RequestBody @Valid UserChangePasswordDTO userChangePasswordDTO, HttpServletRequest request) {
//        User requestedUser = (User) request.getAttribute("user");
//
//        User user = userService.findByUsername(requestedUser.getUsername());
//
//        if (!passwordEncoder.matches(userChangePasswordDTO.getOldPassword(), user.getPassword())) {
//            throw new AppException(Common.MSG_OLD_PASSWORD_INVALID);
//        }
//
//        user.setPassword(passwordEncoder.encode(userChangePasswordDTO.getNewPassword()));
//
//        User updatedUser = userService.updateUser(user);
//
//        return this.resSuccess(updatedUser);
//    }
}
