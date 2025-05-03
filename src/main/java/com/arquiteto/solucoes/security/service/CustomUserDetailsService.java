package com.arquiteto.solucoes.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Exemplo fixo. Ideal: buscar no banco de dados.
        if ("admin".equals(username)) {
            return new User("admin", "{noop}minhaidentificacaodesegurancaeadmin123456789012345678901234567890", Collections.emptyList());
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
