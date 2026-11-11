package com.Aprender.Aprendendo_Oliveira;

public class Faculdade {

    private String nome;
    private String faculdade;
    private String curso;

    public Faculdade(String nome, String faculdade,  String curso) {
        this.nome = nome;
        this.faculdade = faculdade;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public String getFaculdade() {
        return faculdade;
    }

    public String getCurso() {
        return curso;
    }
}
