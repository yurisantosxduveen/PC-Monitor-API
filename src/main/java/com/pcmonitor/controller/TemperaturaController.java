package com.pcmonitor.controller;

import com.pcmonitor.dto.TemperaturaDTO;
import com.pcmonitor.service.TemperaturaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pc/temperatura")
public class TemperaturaController {

    private final TemperaturaService service;

    public TemperaturaController(TemperaturaService service) {
        this.service = service;
    }

    @GetMapping
    public TemperaturaDTO consultarTemperatura() {

       double maxima = service.buscarTemperaturaMaxima();
       double media = service.buscarTemperaturaMedia();

       return new TemperaturaDTO(
               maxima,
               media,
               "°C",
               true
       );

    }

}
