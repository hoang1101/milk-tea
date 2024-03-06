package com.api.milktea.controllers;

import com.api.milktea.models.User;
import com.api.milktea.services.AuthService;
import com.api.milktea.services.ResponseObject;
import com.api.milktea.services.UserService;
import com.api.milktea.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PutMapping("/edit-profile/{id}")
    public ResponseEntity<ResponseObject> editProfile(
            @PathVariable long id,
            @RequestBody User user
    ) {
        try {
            User updateUser = userService.getUserById(id);
            if (updateUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("", "Khong ton tai user", "failed")
                );
            } else {
                if (user.getFullname() != null) {
                    updateUser.setFullname(user.getFullname());
                }
                if (user.getPhone() != null) {
                    updateUser.setPhone(user.getPhone());
                }
                if (user.getAddress() != null) {
                    updateUser.setAddress(user.getAddress());
                }
                if (user.getEmail() != null) {
                    updateUser.setEmail(user.getEmail());
                }
                if (user.getBirthday() != null) {
                    System.out.println(user.getBirthday());
                    updateUser.setBirthday((user.getBirthday()));
                }
                if (user.getGender() != null) {
                    updateUser.setGender(user.getGender());
                }
                updateUser.setIsActive(true);
                userService.updateUser(updateUser);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(updateUser, "Tao thanh cong", "true")
                );
            }


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }


    @GetMapping("/get-all-staff")
    public ResponseEntity<ResponseObject> getAllUserStaff(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(userService.getAllUserStaffService(), "Tao thanh cong", "true")
            );
        } catch  ( Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }


    @GetMapping("/get-all-user")
    public ResponseEntity<ResponseObject> getAllUser(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(userService.getAllUserService(), "Tao thanh cong", "true")
            );
        } catch  ( Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }
}
