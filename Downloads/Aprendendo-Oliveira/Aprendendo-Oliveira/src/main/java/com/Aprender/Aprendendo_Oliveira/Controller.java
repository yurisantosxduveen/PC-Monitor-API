package com.Aprender.Aprendendo_Oliveira;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
public class Controller {

    private final UsuarioService usuarioService;

    public Controller(UsuarioService service) {
        this.usuarioService = service;
    }

    @GetMapping("/usuarios")
    public List<Model> getModel() {
        return usuarioService.buscarUsuario();
        }

    @GetMapping("/faculdade")
    public List<Faculdade> getFaculdadeIdade() {
        return usuarioService.faculdade("Cruzeiro do Sul Virtual");
    }

    @GetMapping("/usuario")
    public Model buscarUsuarioPorNome(@RequestParam String nome) {
        return usuarioService.buscarUsuarioPorNome(nome);
    }

    @PostMapping("/description")
    @ResponseStatus(HttpStatus.CREATED)
    public String get() {
        return usuarioService.description();
    }
}

