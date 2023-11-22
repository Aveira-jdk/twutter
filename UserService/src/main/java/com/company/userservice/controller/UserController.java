package com.company.userservice.controller;

import com.company.userservice.client.AuthClient;
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
    private final AuthClient authClient;

    @PostMapping("/register")
    public void register(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        userService.add(signUpRequestDto);
    }

    @PutMapping("/update")
    public void update(@RequestHeader(name = "Authorization") String authorizationHeader,
                       @RequestBody UserRequestDto userRequestDto) {
        String token = authorizationHeader.replace("Bearer ", "");
        Long userId = authClient.extractId(token);
        userService.update(userId, userRequestDto);
    }

    @DeleteMapping("/delete-user/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @GetMapping("/all-users")
    public List<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        return userService.getAllUsers(page, size);
    }

    @PostMapping("/get-users-by-id")
    public Set<User> getUsersById(@RequestBody Set<Long> usersId) {
        return userService.getUsersById(usersId);
    }

    @GetMapping("/get-user-by-id")
    public User getUserById(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/get-user-by-username")
    public User getUserByUsername(@RequestParam String username){
        return userService.getUserByUsername(username);
    }
}
