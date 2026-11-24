
package com.pcmonitor.controller;

import com.pcmonitor.security.JwtService;
import com.pcmonitor.security.LoginAttemptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private LoginAttemptService loginAttemptService;

    @Test
    void deveRetornarTokenQuandoLoginForValido() throws Exception {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        "visitante",
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                );

        when(loginAttemptService.estaBloqueado("visitante"))
                .thenReturn(false);

        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        when(jwtService.gerarToken(authentication))
                .thenReturn("token-de-teste");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "username": "visitante",
                                    "password": "visitante123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuario").value("visitante"))
                .andExpect(jsonPath("$.permissao").value("USER"))
                .andExpect(jsonPath("$.tipo").value("Bearer"))
                .andExpect(jsonPath("$.token").value("token-de-teste"));
    }
}
