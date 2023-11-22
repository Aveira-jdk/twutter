package com.company.userservice.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequestDto {

    @NotBlank
    String username;

    @NotBlank
    String password;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @Email
    String email;
    int age;
    String contactNumber;
}