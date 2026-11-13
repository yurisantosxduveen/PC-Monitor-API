package com.pcmonitor.controller;

import com.pcmonitor.dto.CpuDTO;
import com.pcmonitor.service.CpuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CpuController {

    private final CpuService service;

    public CpuController(CpuService service) {
        this.service = service;
    }

    @GetMapping("/pc/cpu")
    public CpuDTO buscarCpu(){
        return service.buscarInformacoesCpu();
    }

}
