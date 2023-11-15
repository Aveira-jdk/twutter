package com.company.userservice.controller;

import com.company.userservice.model.entity.Role;
import com.company.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/twutter/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/all-roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
}