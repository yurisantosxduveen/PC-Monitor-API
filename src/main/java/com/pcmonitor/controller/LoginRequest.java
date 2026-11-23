package com.pcmonitor.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotBlank(message = "O nome do usuário é obrigatorio")
        @Size(max = 20, message = "O nome de usuário  deve ter no maxímo 20 caracteres. ")
        String username,

        @NotBlank(message = "A senha é obrigatoria")
        @Size(max = 50, message = "A senha deve ter no maxímo 50 caracteres. ")
        String password
) {

}
