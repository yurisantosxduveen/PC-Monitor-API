package com.pcmonitor.dto;

public record TemperaturaDTO(

        double temperaturaMaxima,
        double temperaturaMedia,
        String unidade,
        boolean disponivel

) {
}
