package com.company.userservice.model.dto.response;

import com.company.userservice.model.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    String firstName;
    String lastName;
    String email;
    String contactNumber;
    Account account;
}