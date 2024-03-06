package com.api.milktea.services;

import com.api.milktea.models.Role;
import com.api.milktea.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findById(long id){
        return roleRepository.findById(id).get();
    }
    public Role getRoleById(long id) {
        try {
            System.out.println(roleRepository.findById(id).get());
            return roleRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }
}
