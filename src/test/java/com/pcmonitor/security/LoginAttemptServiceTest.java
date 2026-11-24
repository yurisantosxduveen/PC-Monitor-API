package com.pcmonitor.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginAttemptServiceTest {

    private LoginAttemptService service;

    @BeforeEach
    void setUp() {
        service = new LoginAttemptService();
    }

    @Test
    void naoDeveBloquearUsuariosSemFalhas() {
        assertFalse(service.estaBloqueado("visitante"));

    }

    @Test
    void naoDeveBloquearUsuarioAposCincoFalhas() {

        for (int i = 0; i < 5; i++) {

            service.registrarFalha("visitante");
        }

        assertTrue(service.estaBloqueado("visitante"));

    }

    @Test
    void deveZerarTentativasAposLoginBemSucedido() {

        for (int i = 0; i < 4; i++) {
            service.registrarFalha("visitante");
        }

        service.registrarSucesso("visitante");

        for (int i = 0; i < 4; i++) {
            service.registrarFalha("visitante");
        }

        assertFalse(service.estaBloqueado("visitante"));
    }

    @Test
    void deveBloquearApenasUsuarioQueCometeuCincoFalhas() {

        for (int i = 0; i < 5; i++) {
            service.registrarFalha("visitante");
        }

        assertTrue(service.estaBloqueado("visitante"));
        assertFalse(service.estaBloqueado("Duveen"));
    }
}
