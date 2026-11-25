package com.pcmonitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErroDeValidacao(MethodArgumentNotValidException exception) {

        List<String> mensagens = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ValidationErrorResponse(
                        "Dados inválidos",
                        mensagens,
                        400
                )
        );
    }


    @ExceptionHandler(
            org.springframework.web.server.ResponseStatusException.class
    )

    public ResponseEntity<ValidationErrorResponse> tratarErroDeRequisicao(
            org.springframework.web.server.ResponseStatusException exception) {

    return ResponseEntity.status(exception.getStatusCode()).body(
            new ValidationErrorResponse(
                    "Muitas tentativas de login ",
                    List.of("Usuários temporariamente bloqueado. Tente novamente em 5 minutos."),
                    exception.getStatusCode().value()
            )
        );

    }
}


