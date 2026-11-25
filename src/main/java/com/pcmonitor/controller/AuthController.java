package com.pcmonitor.controller;

import com.pcmonitor.security.JwtService;
import com.pcmonitor.security.LoginAttemptService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public final JwtService jwtService;
    public final AuthenticationManager authenticationManager;
    private final LoginAttemptService loginAttemptService;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, LoginAttemptService loginAttemptService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.loginAttemptService = loginAttemptService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {


        String username = loginRequest.username();

        if (loginAttemptService.estaBloqueado(username)) {

            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.TOO_MANY_REQUESTS,
                    "Muitas tentativas de login. Tente novamente em 5 minutos. "
            );
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            loginRequest.password()
                    )
            );

            loginAttemptService.registrarSucesso(username);

            String token = jwtService.gerarToken(authentication);

            String permissao = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(authory -> authory.getAuthority().replace("ROLE_", ""))
                    .orElse("USER");

            return new LoginResponse(
                    authentication.getName(),
                    permissao,
                    "Bearer",
                    token
            );

        } catch (org.springframework.security.core.AuthenticationException exception) {
            loginAttemptService.registrarFalha(username);

            throw exception;
        }

    }
}
