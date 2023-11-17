package com.example.authservice.controller;

import com.example.authservice.client.UserClient;
import com.example.authservice.model.User;
import com.example.authservice.security.JwtService;
import com.example.authservice.security.data.AuthRequest;
import com.example.authservice.security.data.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/twutter/AUTH")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;
    private final UserClient userClient;


    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user  = userDetailsService.loadUserByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username){
        return userClient.getUserByUsername(username);
    }

    @GetMapping("/extract-id")
    public String getStr(){
        return "asdfg";
    }
}
