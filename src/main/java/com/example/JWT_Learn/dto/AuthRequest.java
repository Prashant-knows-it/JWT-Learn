package com.example.JWT_Learn.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}