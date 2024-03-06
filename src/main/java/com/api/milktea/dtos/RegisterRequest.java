package com.api.milktea.dtos;

import com.api.milktea.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String phone;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private Role role;



}
