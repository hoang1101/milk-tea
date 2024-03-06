package com.api.milktea.services;

import com.api.milktea.controllers.AuthenticationResponse;
import com.api.milktea.dtos.AuthenticationRequest;
import com.api.milktea.dtos.RegisterRequest;
import com.api.milktea.models.User;
import com.api.milktea.repository.UserRepository;
import com.api.milktea.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    // get all user
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    // check user
    public boolean checkUser (String username) {
        for (User user : getAllUsers()) {
            System.out.println(user.getIsActive());
            if (username.equals(user.getUsername()) && (user.getIsActive())) {
                return true;
            }
        }
        return false;
    }

    public Boolean checkUserExistEmailandPhone(String phone, String email,String username){
        for (User user: getAllUsers()
        ) {
            if(  phone.equals(user.getPhone()) || email.equals(user.getEmail()) ||username.equals(user.getUsername())   )
                return true;
        }
        return  false;

    }

    public Boolean checkUserExistEmailandPhoneById(String phone, String email,String username,String id){
        for (User user: getAllUsers()
        ) {
            if(phone.equals(user.getPhone()) || email.equals(user.getEmail()) || username.equals(user.getUsername()))
                return true;
        }
        return  false;

    }

    public AuthenticationResponse register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phone(request.getPhone())
                .fullname(request.getFullname())
                .password(passwordEncoder.encode( request.getPassword()))
                .role(roleService.getRoleById(2))
//                .isActive(true)
                .build();
        userRepository.save(user);

        var jwtToken = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .user(user)
                .build();



    }


    public AuthenticationResponse loginStaff(AuthenticationRequest request){
        try {
            if (userService.authenticate(request.getUsername(),
                    request.getPassword()
            )) {
                final Optional<User> user = userService.loadUserByUserName(request.getUsername());
                UserDetails userDetails = user.get();
                var jwtToken = jwtUtil.generateToken(userDetails);
                System.out.println(user);
                return AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .user(user)
                        .build();
            } else {
                return AuthenticationResponse.builder()
                        .accessToken("jwtToken")
                        .user("customer")
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
