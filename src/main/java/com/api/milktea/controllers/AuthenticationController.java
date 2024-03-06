package com.api.milktea.controllers;

import com.api.milktea.dtos.AuthenticationRequest;
import com.api.milktea.dtos.RegisterRequest;
import com.api.milktea.models.User;
import com.api.milktea.services.AuthObject;
import com.api.milktea.services.AuthService;
import com.api.milktea.services.ResponseObject;
import com.api.milktea.services.UserService;
import com.api.milktea.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    AuthObject responseHandler = AuthObject.getInstance();
    private final UserService userService;
    private final JwtUtil jwtUtil;


//    AuthenicationResponse authenicationResponse = AuthenicationResponse.getInstance();

    @PostMapping("/registerCustomer")
    public ResponseEntity<ResponseObject> register(
            @RequestBody RegisterRequest request
    ) {
        try {
            System.out.println(request.getPhone());
            if (authService.checkUserExistEmailandPhone(request.getPhone(), request.getEmail(), request.getUsername()))
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("", "Da ton tai", "failed")
                );
            else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(authService.register(request), "Tao thanh cong", "true")
                );

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("", e.getMessage(), "failed")
            );
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Object> loginStaff(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            if (!authService.checkUser(request.getUsername())) {
                return responseHandler.generateAuthenicationResponse("username or password invalid", HttpStatus.BAD_REQUEST, null, null);

            } else {
//                Object data = authService.loginStaff(request);
                final Optional<User> user = userService.loadUserByUserName(request.getUsername());
                UserDetails userDetails = user.get();
                var jwtToken = jwtUtil.generateToken(userDetails);
                return responseHandler.generateAuthenicationResponse("login successfully !", HttpStatus.OK, user, jwtToken);
//                return ResponseEntity.status(HttpStatus.OK).body(
//                        new AuthObject(authService.loginStaff(request), "Dang nhap thanh cong", HttpStatus.OK)
//                );

            }

        } catch (Exception e) {
            return responseHandler.generateAuthenicationResponse("username or password invalid", HttpStatus.BAD_REQUEST, null, null);

        }
    }


}
