package com.pcmonitor.service;

import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.GlobalMemory;

@Service
public class RamService {

    public long buscarMemoriaTotal() {

        SystemInfo sistema = new SystemInfo();

        HardwareAbstractionLayer hardware = sistema.getHardware();

        GlobalMemory memoria = hardware.getMemory();

        // return  memoria.getTotal()/1024; == isso faz com que o OSHI KB

        // Esse faz com que retorne em GB
        return memoria.getTotal() / (1024L * 1024 * 1024);

    }
}
