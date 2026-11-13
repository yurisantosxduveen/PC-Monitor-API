package com.pcmonitor.service;

import com.pcmonitor.dto.SistemaOperacionalDTO;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

import java.util.Locale;

@Service
public class SistemaOperacionalService {

    public SistemaOperacionalDTO buscarInformacoesSistema() {

        SystemInfo sistema = new SystemInfo();

        OperatingSystem sistemaOperacional = sistema.getOperatingSystem();

        String nome =
                sistemaOperacional.getFamily();

        String fabricante =
                sistemaOperacional.getManufacturer();

        String versao =
                sistemaOperacional.getVersionInfo().getVersion();

        String arquitetura =
                System.getProperty("os.arch");

        String usuario =
                System.getProperty("user.name");

        long tempoLigado =
                sistema.getOperatingSystem().getSystemUptime();

        double tempoLigadoHoras =
                tempoLigado / 3600.0;   //  <-- 3600 segundos

        tempoLigadoHoras =
                Math.round(tempoLigadoHoras * 100.0) / 100.0;

        return new SistemaOperacionalDTO(
                nome,
                fabricante,
                versao,
                arquitetura,
                usuario,
                tempoLigadoHoras
        );

    }
}
