package com.pcmonitor.controller;

import com.pcmonitor.dto.ArmazenamentoDTO;
import com.pcmonitor.service.ArmazenamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArmazementoController {

    private final ArmazenamentoService service;

    public ArmazementoController(ArmazenamentoService service) {
        this.service = service;
    }

    @GetMapping("/pc/armazenamento")
    public List<ArmazenamentoDTO> buscarArmazenamento(){
        return service.buscarArmazenamentos();
    }
}
