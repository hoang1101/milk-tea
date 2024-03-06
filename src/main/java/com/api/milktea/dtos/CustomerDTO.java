package com.api.milktea.dtos;

import com.api.milktea.models.Role;
import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String phone;
    private String username;
    private String fullname;
    private String email;
    private Role role;
}
