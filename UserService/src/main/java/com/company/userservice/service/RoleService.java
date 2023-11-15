package com.company.userservice.service;

import com.company.userservice.model.entity.Role;
import com.company.userservice.model.enums.Roles;
import com.company.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByRoleName(Roles eRole) {
        return roleRepository.findByRoleName(eRole);
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }
}