package com.pcmonitor.dto;

public record SistemaOperacionalDTO(

        String nome,
        String fabricante,
        String versao,
        String arquitetura,
        String usuario,
        double tempoLigadoHoras
) {
}
