
package com.pcmonitor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> tratarErroDeValidacao(
            MethodArgumentNotValidException exception) {

        List<String> mensagens = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(
                new ValidationErrorResponse(
                        "Dados inválidos",
                        mensagens,
                        400
                )
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ValidationErrorResponse> tratarErroDeRequisicao(
            ResponseStatusException exception) {

        String mensagem = exception.getReason() != null
                ? exception.getReason()
                : "Erro ao processar requisição";

        return ResponseEntity.status(exception.getStatusCode()).body(
                new ValidationErrorResponse(
                        "Erro na requisição",
                        List.of(mensagem),
                        exception.getStatusCode().value()
                )
        );
    }

    @ExceptionHandler({
            NoHandlerFoundException.class,
            NoResourceFoundException.class
    })
    public ResponseEntity<ValidationErrorResponse> tratarErroNaoEncontrado(
            Exception exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ValidationErrorResponse(
                        "Recurso não encontrado",
                        List.of("O endpoint ou recurso solicitado não existe."),
                        404
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ValidationErrorResponse> tratarErroInterno(
            Exception exception) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ValidationErrorResponse(
                        "Erro interno do servidor",
                        List.of("Ocorreu um erro inesperado. Tente novamente mais tarde."),
                        500
                )
        );
    }
}
