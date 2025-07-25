package com.feirarural.api.user.adapter.in.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.feirarural.api.user.domain.port.out.UserRepository;
import com.feirarural.api.user.domain.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                        .buscarPorEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
        
        String roleComPrefixo = "ROLE_" + user.getTipo().name();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getSenha(),
                List.of(() -> roleComPrefixo)
        );
    }
}
