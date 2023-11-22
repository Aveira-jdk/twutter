package com.company.userservice.service;

import com.company.userservice.model.dto.request.AccountRequestDto;
import com.company.userservice.model.dto.request.SignUpRequestDto;
import com.company.userservice.model.dto.response.AccountResponseDto;
import com.company.userservice.model.entity.Account;
import com.company.userservice.model.entity.Role;
import com.company.userservice.model.enums.Roles;
import com.company.userservice.model.mapper.AccountMapper;
import com.company.userservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public Account add(SignUpRequestDto signUpRequestDto) {
        Role role = roleService.findByRoleName(Roles.ROLE_USER);
        if (!accountRepository.existsByUsername(signUpRequestDto.getUsername())) {
            Account account = accountMapper.signUpRequestDTOtoAccount(signUpRequestDto);
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            account.setRoles(roles);
            return accountRepository.save(account);
        }
        throw new RuntimeException("Account not saved");
    }

    public void addRole(String id, String roleName) {
        Roles eRole = Roles.ROLE_ADMIN;
        if (roleName.equalsIgnoreCase("ROLE_USER"))
            eRole = Roles.ROLE_USER;
        Role role = roleService.findByRoleName(eRole);
        if (!accountRepository.existsByRoles(role)){
            Account account = accountRepository.findById(id).get();
            account.getRoles().add(role);
            accountRepository.save(account);
        }
    }

    public void deleteRole(String id, String roleName) {
        Roles eRole = Roles.ROLE_ADMIN;
        if (roleName.equalsIgnoreCase("ROLE_USER"))
            eRole = Roles.ROLE_USER;
        Role role = roleService.findByRoleName(eRole);
        if (accountRepository.existsByRoles(role)){
            Account account = accountRepository.findById(id).get();
            account.getRoles().remove(role);
            accountRepository.save(account);
        }
    }

    public Account update(String id, AccountRequestDto accountRequestDto) {
        Account account = accountRepository.findById(id).get();
        if (accountRequestDto.getUsername() != null && accountRequestDto.getUsername() != account.getUsername())
            account.setUsername(accountRequestDto.getUsername());
        if (accountRequestDto.getPassword() != null)
            account.setPassword(accountRequestDto.getPassword());
        return accountRepository.save(account);
    }

    public void delete(Account account) {
        account.setRoles(null);
        roleService.deleteAccount(account.getId());
        accountRepository.delete(account);
    }

    public AccountResponseDto getByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return accountMapper.accountToAccountResponseDTO(account);
    }

    public List<Account> getByRoleName(String role) {
        if (role.equalsIgnoreCase("USER"))
            return accountRepository.findAccountsByRoles(new Role(1L, Roles.ROLE_USER));
        else if (role.equalsIgnoreCase("ADMIN"))
            return accountRepository.findAccountsByRoles(new Role(2L, Roles.ROLE_ADMIN));
        else return null;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

}