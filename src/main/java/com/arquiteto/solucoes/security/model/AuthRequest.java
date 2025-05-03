package com.arquiteto.solucoes.security.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;

    // Getters e Setters
}
