package com.pcmonitor.controller;

import com.pcmonitor.dto.RedeDTO;
import com.pcmonitor.service.RedeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pc/rede")
public class RedeController {

    private final RedeService service;

    public RedeController(RedeService service) {
        this.service = service;
    }

    @GetMapping
    public RedeDTO consultarRede(){
        return service.consultarRede();
    }

}
