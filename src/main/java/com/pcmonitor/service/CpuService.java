package com.pcmonitor.service;

import com.pcmonitor.dto.CpuDTO;
import oshi.SystemInfo;
import org.springframework.stereotype.Service;
import oshi.hardware.CentralProcessor;

@Service
public class CpuService {

    public CpuDTO buscarInformacoesCpu() {

        SystemInfo sistema = new SystemInfo();

        CentralProcessor processador =
                sistema.getHardware().getProcessor();

        CentralProcessor.ProcessorIdentifier identificacao =
                processador.getProcessorIdentifier();

        double usoCpu =
                processador.getSystemCpuLoad(1000L) * 100;

        double porcentualUso =
                Math.round(usoCpu * 100) / 100.0;

        return new CpuDTO(
                identificacao.getName(),
                identificacao.getVendor(),
                processador.getPhysicalPackageCount(),
                processador.getLogicalProcessorCount(),
                porcentualUso
        );
    }
}
