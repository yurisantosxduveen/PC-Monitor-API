package com.pcmonitor.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DesligamentoService {

    public void desligarComputador() throws IOException, InterruptedException {

        Process processo = new ProcessBuilder(
                "shutdown", "/s", "/t", "60")
                .start();

                int resultado = processo.waitFor();

                if (resultado != 0) {
                    throw new IOException(
                            "Não foi possível iniciar o desligamento do computador."
                    );
                }
    }

    public void cancelarDesligamento () throws IOException, InterruptedException {

        Process processo = new ProcessBuilder(
                "shutdown", "/a")
                .start();

        int resultado = processo.waitFor();

        if (resultado != 0) {
            throw new IOException(
                    "Não foi possível cancelar o desligamento do computador."
            );

        }

    }

}
