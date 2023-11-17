package com.company.userservice.controller;

import com.company.userservice.model.dto.request.AccountRequestDto;
import com.company.userservice.model.dto.response.AccountResponseDto;
import com.company.userservice.model.entity.Account;
import com.company.userservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/twutter/USER/accounts")
public class AccountController {

    private final AccountService accountService;

    @PutMapping("/{id}")
    public void update(@PathVariable String id,
                       @RequestBody AccountRequestDto accountRequestDto){
        accountService.update(id, accountRequestDto);
    }

    @PatchMapping("add-role/{id}")
    public void addRole(@PathVariable String id, @RequestParam String roleName){
        accountService.addRole(id, roleName);
    }

    @PatchMapping("delete-role/{id}")
    public void deleteRole(@PathVariable String id, @RequestParam String roleName){
        accountService.addRole(id, roleName);
    }

    @GetMapping("/get-by-username")
    public AccountResponseDto getByUsername(@RequestParam String username){
        return accountService.getByUsername(username);
    }

    @GetMapping("/get-by-role-name")
    public List<Account> getByRoleName(@RequestParam String role){
        return accountService.getByRoleName(role);
    }

    @GetMapping("/all-accounts")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }



}