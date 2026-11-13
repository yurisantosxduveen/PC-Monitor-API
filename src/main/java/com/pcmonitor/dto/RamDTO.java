package com.pcmonitor.dto;

public record RamDTO(

        double memoriaTotal,
        double memoriaDisponivel,
        double memoriaUtilizada,
        double porcentualUso,
        String unidade
) {
}
