package com.company.userservice.model.mapper;

import com.company.userservice.model.dto.request.SignUpRequestDto;
import com.company.userservice.model.dto.response.AccountResponseDto;
import com.company.userservice.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "username", source = "signUpRequestDTO.username")
    Account signUpRequestDTOtoAccount(SignUpRequestDto signUpRequestDTO);

    AccountResponseDto accountToAccountResponseDTO(Account account);
}