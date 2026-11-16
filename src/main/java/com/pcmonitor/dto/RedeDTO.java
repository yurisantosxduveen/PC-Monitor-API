package com.pcmonitor.dto;

public record RedeDTO(
        String nome,
        String fabricante,
        String enderecoMac,
        String enderecoIp,
        boolean conectada
) {
}
