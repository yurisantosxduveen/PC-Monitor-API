package com.pcmonitor.service;

import com.pcmonitor.dto.RamDTO;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.GlobalMemory;

@Service
public class RamService {

    public RamDTO buscarMemoriaTotal() {

        SystemInfo sistema = new SystemInfo();

        HardwareAbstractionLayer hardware = sistema.getHardware();

        GlobalMemory memoria = hardware.getMemory();

        long totalBytes = memoria.getTotal();

        long disponivelBytes = memoria.getAvailable();

        long utilizadaBytes = totalBytes - disponivelBytes;

        double totalGB = totalBytes / (1024.0 * 1024.0 * 1024.0);

        double disponivelGB = disponivelBytes / (1024.0 * 1024 * 1024);

        double utilizadaGB = utilizadaBytes / (1024.0 * 1024 * 1024);

        double porcentualUso = (utilizadaBytes * 100.0) / totalBytes;

        return new RamDTO(
                Math.round(totalGB * 100.0) / 100.0,
                Math.round(disponivelGB * 100.0) / 100.0,
                Math.round(utilizadaGB * 100.0) / 100.0,
                Math.round(porcentualUso * 100.0) / 100.0,
                "GB"
        );

    }
}
