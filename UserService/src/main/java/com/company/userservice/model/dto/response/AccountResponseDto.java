package com.company.userservice.model.dto.response;

import com.company.userservice.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

    String username;
    String password;
    boolean isActive;
    Set<Role> roles;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}