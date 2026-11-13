package com.pcmonitor.controller;

import com.pcmonitor.dto.SistemaOperacionalDTO;
import com.pcmonitor.service.SistemaOperacionalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SistemaOperacionalController {

    private final SistemaOperacionalService service;

    public SistemaOperacionalController(
            SistemaOperacionalService service) {
        this.service = service;
    }
    @GetMapping("/pc/sistema-operacional")
    public SistemaOperacionalDTO buscarSistema() {
        return service.buscarInformacoesSistema();
    }
}
