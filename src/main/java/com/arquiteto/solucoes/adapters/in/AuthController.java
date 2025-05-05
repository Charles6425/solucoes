package com.arquiteto.solucoes.adapters.in;

import com.arquiteto.solucoes.security.jwt.JwtTokenProvider;
import com.arquiteto.solucoes.security.model.AuthRequest;
import com.arquiteto.solucoes.security.model.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável por gerenciar endpoints de autenticação.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;



    @Operation(summary = "Endpoint para autenticação de usuários.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um objeto  contendo o token JWT gerado."),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = tokenProvider.generateToken(authentication.getName());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
