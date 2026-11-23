package com.pcmonitor.exception;

import java.util.List;

public record ValidationErrorResponse(

        String erro,
        List<String> mensagens,
        int status
) {
}
