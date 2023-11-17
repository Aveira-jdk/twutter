package com.company.userservice.controller;

import com.company.userservice.model.dto.request.SignUpRequestDto;
import com.company.userservice.model.dto.request.UserRequestDto;
import com.company.userservice.model.entity.User;
import com.company.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/twutter/USER/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        userService.add(signUpRequestDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        userService.update(id, userRequestDto);
    }

    @DeleteMapping
    public void delete(@RequestParam String username) {
        userService.delete(username);
    }

    @GetMapping("/all-users")
    public List<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        return userService.getAllUsers(page, size);
    }

    @PostMapping("/getUsersById")
    public Set<User> getUsersById(@RequestBody Set<Long> usersId) {
        return userService.getUsersById(usersId);
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }
}
