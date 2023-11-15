package com.company.userservice.model.mapper;

import com.company.userservice.model.dto.request.SignUpRequestDto;
import com.company.userservice.model.dto.response.UserResponseDto;
import com.company.userservice.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User signUpRequestDtoToUser(SignUpRequestDto signUpRequestDto);

    UserResponseDto userToUserResponseDto(User user);
}