package com.pcmonitor.agent.controller;

import com.pcmonitor.agent.service.CpuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pc/cpu")
public class CpuController {

    private final CpuService service;

    public CpuController(CpuService service) {
        this.service = service;
    }

    @GetMapping
    public double buscarCpu() {
        return service.getUsoCpu();

    }
}
