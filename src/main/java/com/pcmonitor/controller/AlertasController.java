package com.pcmonitor.controller;

import com.pcmonitor.dto.AlertaDTO;
import com.pcmonitor.service.AlertasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pc/Alertas")
public class AlertasController {

    private final AlertasService alertasService;

    public AlertasController(AlertasService alertasService) {
        this.alertasService = alertasService;
    }

    @GetMapping
    public List<AlertaDTO> ConsultarAlertas() {
        return alertasService.buscarAlertas();
    }

}
