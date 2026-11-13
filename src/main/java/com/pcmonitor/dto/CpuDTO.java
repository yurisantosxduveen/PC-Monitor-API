package com.pcmonitor.dto;

public record CpuDTO(

        String nome,
        String fabricante,
        int nucleosFisicos,
        int nucleosLogicos,
        double porcentualUso
) {
}
