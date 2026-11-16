package com.pcmonitor.service;

import com.pcmonitor.dto.CpuDTO;
import com.pcmonitor.dto.RamDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MonitoramentoService {

    private final CpuService cpuService;
    private final RamService ramService;
    private final HistoricoService historicoService;
    private final AlertasService alertasService;

    public MonitoramentoService(CpuService cpuService, RamService ramService, HistoricoService historicoService, AlertasService alertasService) {

        this.cpuService = cpuService;
        this.ramService = ramService;
        this.historicoService = historicoService;
        this.alertasService = alertasService;
    }

    @Scheduled(fixedRate = 5000)
    public void monitorarHardware() {

        CpuDTO cpu = cpuService.buscarInformacoesCpu();

        RamDTO ram = ramService.buscarMemoriaTotal();

        historicoService.salvarHistorico(cpu, ram);

        alertasService.verificarAlertas(cpu, ram);
    }



}
