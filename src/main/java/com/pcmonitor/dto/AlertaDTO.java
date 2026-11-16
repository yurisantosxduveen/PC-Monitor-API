package com.pcmonitor.dto;

public record AlertaDTO(

        String componente,
        String mensagem,
        double percentualUso

) {
}
