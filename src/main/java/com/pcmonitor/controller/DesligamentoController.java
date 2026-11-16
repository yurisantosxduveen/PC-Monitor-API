package com.pcmonitor.controller;

import com.pcmonitor.service.DesligamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pc/desligar")
public class DesligamentoController {

    private final DesligamentoService service;

    public DesligamentoController(DesligamentoService service) {
        this.service = service;
    }

    @PostMapping
    public Map<String, String> desligar(
            @RequestParam("confirmar") boolean confirmar) throws Exception {

        if (!confirmar) {
            return Map.of("mensagem", "Desligado não confirmado.");
        }

        service.desligarComputador();

        return Map.of(
                "mensagem", "Computador será desligado em 60 segundos.");

    }

    @PostMapping("/cancelar")
    public Map<String, String> cancelar() throws Exception {

    service.cancelarDesligamento();
        return Map.of(
                "mensagem", "Desligamento cancelado."
        );

    }
}
