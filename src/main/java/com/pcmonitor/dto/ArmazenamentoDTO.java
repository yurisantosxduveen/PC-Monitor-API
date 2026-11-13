package com.pcmonitor.dto;

public record ArmazenamentoDTO(

        String nome,
        String pontoMontagem,
        double espacoTotal,
        double espacoDisponivel,
        double espacoUtilizado,
        double porcentualUso,
        String unidade
) {
}
