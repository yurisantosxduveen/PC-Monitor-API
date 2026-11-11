# 📚 Aprendendo Spring Boot

Este projeto foi criado com o objetivo de praticar os conceitos básicos do **Spring Boot** e entender melhor como funciona uma API REST em Java.

Durante o desenvolvimento fui colocando em prática conceitos como:

- Organização em camadas (Controller, Service e Model);
- Criação de endpoints REST;
- Manipulação de listas de objetos;
- Busca de dados utilizando parâmetros na URL;
- Uso de Streams para filtrar informações;
- Retorno de objetos e listas em formato JSON.

O foco principal deste projeto é o aprendizado e a evolução na programação com Java e Spring Boot.

---

## 🚀 Tecnologias utilizadas

- Java
- Spring Boot
- Maven
- IntelliJ IDEA
- Postman

---

## 📁 Estrutura do projeto

```
src
 ├── Controller.java
 ├── UsuarioService.java
 ├── Model.java
 ├── Faculdade.java
 └── AprendendoOliveiraApplication.java
```

### Controller

Responsável por receber as requisições HTTP e encaminhá-las para a camada de serviço.

### UsuarioService

Contém toda a lógica da aplicação.

Nele estão cadastradas listas de usuários e estudantes, além dos métodos responsáveis pelas buscas e filtros.

### Model

Classe que representa um usuário.

Atributos:

- Usuário
- Senha
- Idade

### Faculdade

Classe responsável por representar um estudante.

Atributos:

- Nome
- Faculdade
- Curso

---

# Endpoints

## Buscar todos os usuários

```
GET /usuarios
```

Exemplo de retorno:

```json
[
  {
    "usuario": "Diego",
    "senha": 2006,
    "idade": 18
  },
  {
    "usuario": "Wikelme",
    "senha": 2610,
    "idade": 18
  }
]
```

---

## Buscar usuários por faculdade

```
GET /faculdade
```

Retorna apenas os alunos da faculdade cadastrada.

---

## Buscar usuário pelo nome

```
GET /usuario?nome=Diego
```

Exemplo:

```
GET /usuario?nome=Wikelme
```

Caso o usuário exista, ele será retornado.

---

## Descrição do projeto

```
POST /description
```

Retorna uma breve descrição da API.

---

# O que aprendi

Durante este projeto consegui praticar diversos conceitos importantes do Spring Boot, como:

- Criação de APIs REST;
- Utilização das anotações do Spring;
- Separação de responsabilidades entre Controller e Service;
- Criação de modelos (Models);
- Manipulação de listas utilizando `List`;
- Busca de dados utilizando `Stream`;
- Uso do método `filter()`;
- Uso do método `findFirst()`;
- Retorno de objetos em JSON.

---

# Próximos passos

Pretendo continuar evoluindo este projeto adicionando funcionalidades como:

- Cadastro de usuários;
- Atualização de dados (PUT);
- Exclusão de usuários (DELETE);
- Banco de dados utilizando MySQL;
- JPA/Hibernate;
- Validação de dados;
- Tratamento de exceções;
- Documentação com Swagger.

---

# Objetivo

Este projeto faz parte dos meus estudos em Java e Spring Boot.

A ideia é ir evoluindo a aplicação conforme vou aprendendo novos conceitos, transformando uma API simples em um projeto cada vez mais completo.