package com.example.authservice.controller;

import com.example.authservice.security.JwtService;
import com.example.authservice.security.data.AuthRequest;
import com.example.authservice.security.data.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;

    @Qualifier("securityFilterChain")
    private final SecurityFilterChain filterChain;

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

    @GetMapping("/filter-chain")
    public SecurityFilterChain filterChain(){
        return filterChain;
    }
}
