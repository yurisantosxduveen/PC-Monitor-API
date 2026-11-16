package com.pcmonitor.service;

import com.pcmonitor.dto.CpuDTO;
import com.pcmonitor.dto.HistoricoDTO;
import com.pcmonitor.dto.RamDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoService {

    private List<HistoricoDTO> historico = new ArrayList<>();

    public void salvarHistorico(CpuDTO cpu, RamDTO ram ) {

        HistoricoDTO registro = new HistoricoDTO(
                LocalDateTime.now(),
                cpu.porcentualUso(),
                ram.porcentualUso()
        );

        historico.add(registro);

    }

    public List<HistoricoDTO> buscarHistorico() {
        return historico;
    }




}
