package com.pcmonitor.controller;

public record LoginResponse(

        String usuario,
        String permissao,
        String tipo,
        String token

) {
}
