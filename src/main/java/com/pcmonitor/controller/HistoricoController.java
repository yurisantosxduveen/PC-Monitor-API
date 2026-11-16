package com.pcmonitor.controller;

import com.pcmonitor.dto.HistoricoDTO;
import com.pcmonitor.service.HistoricoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pc/historico")
public class HistoricoController {

    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @GetMapping
    public List<HistoricoDTO> buscarHistorico() {
        return historicoService.buscarHistorico();
    }
}
