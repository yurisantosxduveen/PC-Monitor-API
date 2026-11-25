package com.pcmonitor.security;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginAttemptService {

    private static final int MAX_TENTATIVAS = 5;
    private static final long TEMPO_BLOQUEIO_MINUTOS = 5;

    private final Map<String, Tentativas> tentativas = new ConcurrentHashMap<>();

    public boolean estaBloqueado(String username) {

        Tentativas registro = tentativas.get(username);

            if (registro == null || registro.bloqueadoAte() == null) {
                return false;
            }

            if (Instant.now().isBefore(registro.bloqueadoAte())) {
                return true;
            }

            tentativas.remove(username, registro);

            return false;
        }

    public void registrarFalha(String username) {
        tentativas.compute(username, (chave, atual) -> {

            int quantidade = atual == null
                ? 1
                : atual.quantidade() + 1;

            Instant bloqueio = quantidade >= MAX_TENTATIVAS
                    ? Instant.now().plusSeconds(TEMPO_BLOQUEIO_MINUTOS * 60)
                    : null;

            return new Tentativas(quantidade, bloqueio);

        });
    }

    public void registrarSucesso(String username) {
        tentativas.remove(username);
    }

    private record Tentativas(
            int quantidade,
            Instant bloqueadoAte) {}
}
