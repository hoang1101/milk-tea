package com.api.milktea.services;

import com.api.milktea.models.User;
import com.api.milktea.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public Optional<User> loadUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }


    public Boolean authenticate(String username, String password) throws Exception {
        try {
            try {

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Tên tài khoản hoặc mật khẩu không chính xác.", e);
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUserStaffService() {
        return (List<User>) userRepository.findByRoleId(2);
    }

    public List<User> getAllUserService() {
        return (List<User>) userRepository.findByRoleId(1);
    }



    public User updateUser(User user) {
        return userRepository.save(user);
    }


}
