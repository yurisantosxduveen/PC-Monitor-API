package com.pcmonitor.controller;

import com.pcmonitor.service.RamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pcmonitor.dto.RamDTO;

@RestController
public class RamController {

    private final RamService service;

    public RamController(RamService service) {
        this.service = service;
    }

    @GetMapping("/pc/ram")
    public RamDTO buscarRam() {
        return service.buscarMemoriaTotal();

    }

}
