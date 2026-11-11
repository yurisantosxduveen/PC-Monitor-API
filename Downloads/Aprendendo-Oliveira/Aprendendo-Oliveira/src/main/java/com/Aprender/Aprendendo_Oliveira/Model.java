package com.Aprender.Aprendendo_Oliveira;

public class Model {

    private String usuario;
    private int senha;
    private int idade;

        public Model(String usuario, int senha, int idade) {
            this.usuario = usuario;
            this.senha = senha;
            this.idade = idade;
        }

        public String getUsuario() {
            return usuario;
        }

        public int getSenha() {
            return senha;
        }

    public int getIdade() {
        return idade;
    }
}
