package com.pcmonitor.service;

import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class TemperaturaService {

    private final ObjectMapper mapper = new ObjectMapper();

    private final HttpClient client = HttpClient.newHttpClient();

    private JsonNode consultarSensores() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8085/data.json"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IllegalStateException("Erro ao consultar sensores. Status: "
                    + response.statusCode());
        }

        return mapper.readTree(response.body());
    }

    public double buscarTemperaturaMaxima() {
        return buscarTemperatura("/intelcpu/0/temperature/0");
    }

    public double buscarTemperaturaMedia() {
        return buscarTemperatura("/intelcpu/0/temperature/1");
    }

    private double buscarTemperatura(String sensorId) {

        try {
            JsonNode dados = consultarSensores();
            JsonNode sensor = encontrarSensor(dados, sensorId);

            if (sensor == null) {
                throw  new IllegalStateException("Sensor não encontrado: " + sensorId);
            }

            String valor = sensor.path("Value").asText();

            System.out.println("Valor recebido: [" + valor + "]");

            return Double.parseDouble(
                    valor.replace("°C", "")
                            .replace(",", ".")
                            .trim()
            );

        }   catch (IOException e) {
                throw new  IllegalStateException("Erro ao consultar sensores");
        }   catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new  IllegalStateException("Consulta interrompida.", e);
        }
    }

    private JsonNode encontrarSensor(JsonNode node, String sensorId) {

        if (node.isObject()) {

            if (sensorId.equals(node.path("SensorId").asText())) {
                return node;
            }

            for (JsonNode filho : node) {
                JsonNode resultado = encontrarSensor(filho, sensorId);

                if (resultado != null) {
                    return resultado;
                }
            }
        }

        if (node.isArray()) {
            for (JsonNode filho : node) {
                JsonNode resultado = encontrarSensor(filho, sensorId);

                if (resultado != null) {
                    return resultado;
                }
            }
        }

        return null;
    }
}
















