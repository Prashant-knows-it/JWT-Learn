package com.example.JWT_Learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JWT_Learn.dto.AuthRequest;
import com.example.JWT_Learn.dto.AuthResponse;
import com.example.JWT_Learn.model.Human;
import com.example.JWT_Learn.repository.HumanRepository;
import com.example.JWT_Learn.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HumanRepository humanRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtUtil.generateToken(authentication);
        String refreshToken = jwtUtil.generateRefreshToken(authentication);
        return new AuthResponse(token, refreshToken);
    }

    @PostMapping("/register")
    public String register(@RequestBody Human human) {
        human.setPassword(passwordEncoder.encode(human.getPassword()));
        humanRepository.save(human);
        return "User registered successfully!";
    }
}