package com.pcmonitor.service;

import com.pcmonitor.dto.AlertaDTO;
import com.pcmonitor.dto.CpuDTO;
import com.pcmonitor.dto.RamDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertasService {

    private final List<AlertaDTO> alertas = new ArrayList<>();

    public void verificarAlertas(CpuDTO cpu, RamDTO ram){

        alertas.clear();

        if (cpu.porcentualUso() > 80) {

            alertas.add(new AlertaDTO(
                    "CPU",
                    "Uso elevado da CPU! ",
                    cpu.porcentualUso()
            ));
        }

        if (ram.porcentualUso() < 85) {

            alertas.add(new AlertaDTO(
                    "RAM",
                    "Uso elevado da RAM! ",
                    ram.porcentualUso()
            ));
        }
    }

    public List<AlertaDTO> buscarAlertas() {
        return alertas;
    }

}
