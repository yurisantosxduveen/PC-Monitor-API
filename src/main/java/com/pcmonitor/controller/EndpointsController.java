
package com.pcmonitor.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
public class EndpointsController {

    private final RequestMappingHandlerMapping handlerMapping;

    public EndpointsController(
            @Qualifier("requestMappingHandlerMapping")
            RequestMappingHandlerMapping handlerMapping) {

        this.handlerMapping = handlerMapping;
    }

    @GetMapping(value = "/pc/endpoints", produces = MediaType.TEXT_HTML_VALUE)
    public String listarEndpoints() {

        StringBuilder html = new StringBuilder();

        html.append("""
                <html>
                <head>
                    <title>Endpoints da API</title>
                    <style>
                        body { font-family: Arial; padding: 30px; }
                        table { border-collapse: collapse; width: 100%; }
                        th, td { border: 1px solid #ddd; padding: 12px; }
                        th { background-color: #333; color: white; }
                    </style>
                </head>
                <body>
                    <h1>PC Monitor API - Endpoints</h1>
                    <table>
                        <tr>
                            <th>Controller</th>
                            <th>Método HTTP</th>
                            <th>Rota</th>
                        </tr>
                """);

        handlerMapping.getHandlerMethods().forEach((info, metodo) -> {

            String controller = metodo.getBeanType().getSimpleName();

            String metodosHttp = info.getMethodsCondition()
                    .getMethods()
                    .stream()
                    .map(RequestMethod::name)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("TODOS");

            for (String rota : info.getPatternValues()) {

                html.append("<tr>");
                html.append("<td>").append(controller).append("</td>");
                html.append("<td>").append(metodosHttp).append("</td>");
                html.append("<td>").append(rota).append("</td>");
                html.append("</tr>");
            }
        });

        html.append("""
                    </table>
                </body>
                </html>
                """);

        return html.toString();
    }
}
