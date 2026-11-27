package com.pcmonitor.agent.service;

import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

@Service
public class CpuService {

    SystemInfo sistema =  new SystemInfo();

    CentralProcessor processador =
            sistema.getHardware().getProcessor();

    public double getUsoCpu() {

        double buscarUsoCpu =
                processador.getSystemCpuLoad(1000L) * 100;

        double usoCpu =
                Math.round(buscarUsoCpu * 100) / 100.0;

        return usoCpu;

    }

}
