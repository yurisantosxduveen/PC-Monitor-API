package com.pcmonitor.dto;

import java.time.LocalDateTime;

public record HistoricoDTO(

        LocalDateTime timestamp,
        double percentualCpu,
        double percentualRam

) {
}