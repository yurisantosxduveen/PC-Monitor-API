package com.Aprender.Aprendendo_Oliveira;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @org.jetbrains.annotations.Unmodifiable
    @org.jetbrains.annotations.Contract(" -> new")
    public @NonNull List<Model> buscarUsuario() {
        return List.of(
                new Model("Yuri", 1108, 18),
                new Model("Letícia", 1009, 18),
                new Model("Andressa", 1804, 18),
                new Model("Vitor", 1910,18),
                new Model("Diego", 2006, 18),
                new Model("Wikelme", 2610, 18)
        );
    }

    public List<Faculdade> faculdade(String nomeFaculdade) {
        List<Faculdade> usuarios = List.of(
                new Faculdade("Yuri Olivera Santos ", "Cruzeiro do Sul Virtual ", "Engenharia de Software"),
                new Faculdade("Letícia Andrede Souza ", "Cruzeiro do Sul Virtual ", "Engenharia de Software"),
                new Faculdade("Andressa Pereira de Souza ", "Cruzeiro do Sul Virtual ", "Engenharia de Software"),
                new Faculdade("Vitor Ugo Souza de Paula ", "Cruzeiro do Sul Virtual ", "Engenharia de Software"),
                new Faculdade("Diego Moura da Silva ", "Cruzeiro do Sul Virtual ", "Engenharia de Software"),
                new Faculdade("Wikelme Silva Correia ", "Cruzeiro do Sul Virtual ", "Engenharia de Software")
        );

        return usuarios.stream()
                .filter(u -> u.getFaculdade().trim()
                        .equalsIgnoreCase(nomeFaculdade.trim()))
                .toList();
    }

    public String description() {
        return "Descrição do Projeto:" +
                " Este projeto é uma API REST desenvolvida em Java com Spring Boot, responsável por gerenciar e retornar informações de usuários, incluindo lista de usuários, filtro por idade e uma mensagem descritiva.";
    }

    public Model buscarUsuarioPorNome(String nome) {

        List<Model> usuarios = buscarUsuario();

        return usuarios.stream()
                .filter(u -> u.getUsuario().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}