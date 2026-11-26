# PC Monitor API

API REST desenvolvida em Java com Spring Boot para monitoramento e gerenciamento de informações do computador em tempo real.

O projeto foi criado com o objetivo de praticar desenvolvimento Backend, construção de APIs REST, monitoramento de hardware, autenticação e segurança utilizando Spring Security e JWT.
Atualmente, a aplicação permite consultar informações do computador, acompanhar métricas de desempenho, armazenar históricos de monitoramento e executar operações administrativas protegidas por autenticação.

---

## Funcionalidades

### Monitoramento do computador

- Monitoramento do uso da CPU.
- Monitoramento da memória RAM.
- Consulta de memória total, disponível e utilizada.
- Cálculo percentual de utilização da memória.
- Monitoramento de armazenamento.
- Consulta de informações do sistema operacional.
- Monitoramento de temperatura do processador, conforme disponibilidade dos sensores.
- Monitoramento da conexão com a internet.

### Histórico e alertas

- Registro periódico de informações de CPU e RAM.
- Coleta automática de métricas a cada 5 segundos.
- Consulta de históricos de monitoramento.
- Verificação de limites de utilização.
- Sistema de alertas para níveis elevados de consumo.

### Segurança e autenticação

- Autenticação utilizando JWT (JSON Web Token).
- Spring Security.
- Senhas protegidas com BCrypt.
- Autorização baseada em papéis (Roles).
- Usuário ADMIN e usuário USER.
- API configurada para trabalhar sem sessões, utilizando autenticação stateless.
- Tratamento personalizado de erros HTTP 401 e 403.
- Controle de tentativas de login.
- Limitação de tentativas de autenticação.

### Gerenciamento do computador

- Solicitação de desligamento do computador.
- Agendamento de desligamento para 60 segundos.
- Cancelamento de desligamento agendado.
- Operações administrativas protegidas por autorização ADMIN.

---

## Tecnologias utilizadas

| Tecnologia | Utilização |
|---|---|
| Java 25 | Linguagem principal |
| Spring Boot 4.0.9 | Framework Backend |
| Spring Web MVC | Construção da API REST |
| Spring Security | Segurança e autorização |
| JWT | Autenticação por tokens |
| BCrypt | Criptografia de senhas |
| OSHI | Monitoramento de hardware |
| Maven | Gerenciamento de dependências |
| Lombok | Redução de código repetitivo |
| Spring Validation | Validação de dados |
| Springdoc OpenAPI | Documentação da API |
| JUnit 5 | Testes automatizados |
| Mockito | Simulação de dependências |
| MockMvc | Testes dos endpoints |
| Postman | Testes das requisições HTTP |
| Git e GitHub | Versionamento do projeto |

---

## Arquitetura do projeto

O projeto utiliza uma organização em camadas para separar as responsabilidades e facilitar a manutenção do código.

```text
src
 ├── main
 │    ├── java
 │    │    └── com.pcmonitor
 │    │         ├── config
 │    │         ├── controller
 │    │         ├── dto
 │    │         ├── exception
 │    │         ├── secutity
 │    │         └── service
 │    │
 │    └── resources
 │         └── application.yaml
 │
 └── test
      └── java
            └── com.pcmonitor
                     ├── controller
                     ├── sercurity
                     
```

### Responsabilidades das camadas

- **config:** configurações da aplicação e segurança.
- **controller:** recebe e processa as requisições HTTP.
- **dto:** objetos utilizados para transportar dados.
- **exception:** tratamento personalizado de exceções.
- **security:** componentes relacionados à autenticação e autorização.
- **service:** regras de negócio e comunicação com recursos do sistema.

---

## Autenticação JWT

A API utiliza JWT para autenticar os usuários e controlar o acesso aos recursos protegidos.

### 1. Realizar login

Endpoint:

```http
POST /auth/login
```

O usuário envia suas credenciais e, após a autenticação, recebe um token JWT.

### 2. Utilizar o token

No Postman:

1. Acesse a requisição desejada.
2. Entre na aba Authorization.
3. Selecione Bearer Token.
4. Informe o token recebido no login.
5. Execute a requisição.

### 3. Controle de acesso

A aplicação possui dois níveis de autorização:

| Perfil | Permissões |
|---|---|
| ADMIN | Acesso aos recursos administrativos e demais recursos autenticados |
| USER | Acesso aos recursos permitidos para usuários comuns |

As senhas e a chave JWT são configuradas por variáveis de ambiente, evitando deixar credenciais reais diretamente no código-fonte.

---

## Principais endpoints

Abaixo estão algumas das rotas principais da aplicação.

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/auth/login` | Realiza autenticação e gera JWT |
| GET | `/pc/ram` | Consulta informações da memória RAM |
| POST | `/pc/desligar?confirmar=true` | Agenda o desligamento do computador |
| POST | `/pc/desligar/cancelar` | Cancela o desligamento agendado |
| GET | `/pc/endpoints` | Consulta a listagem de endpoints |

Outros recursos incluem monitoramento de CPU, armazenamento, temperatura, sistema operacional, rede, histórico e alertas.

A lista completa das rotas deve ser consultada na documentação OpenAPI/Swagger da aplicação.

---

## Documentação online

A documentação visual do projeto está disponível no GitHub Pages:
 
https://yurisantosxduveen.github.io/PC-Monitor-API/

A página apresenta a documentação da API para consulta.

**Importante:** a documentação publicada no GitHub Pages é estática. Ela não hospeda o Backend. API continua sendo executada localmente no computador onde o projeto está instalado.

---

## Como executar o projeto

### Pré-requisitos

Antes de executar, tenha instalado:

- JDK 25.
- IntelliJ IDEA ou outra IDE compatível com Java.
- Git.
- Maven (ou utilize o Maven Wrapper incluído no projeto).

### 1. Clonar o repositório

```bash
git clone https://github.com/yurisantosxduveen/PC-Monitor-API.git
```

### 2. Entrar na pasta

```bash
cd PC-Monitor-API
```

### 3. Configurar as variáveis de ambiente

Antes de iniciar a aplicação, configure as seguintes variáveis de ambiente:

| Variável | Finalidade |
|---|---|
| `JWT_SECRET` | Chave utilizada para assinar e validar os tokens JWT |
| `ADMIN_USERNAME` | Nome de usuário administrativo |
| `ADMIN_PASSWORD` | Senha do administrador |
| `USER_USERNAME` | Nome de usuário comum |
| `USER_PASSWORD` | Senha do usuário comum |

Utilize valores próprios e seguros.

**Nunca publique suas senhas ou sua chave JWT no GitHub.**

### 4. Executar a aplicação

No Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Ou execute a classe principal pela sua IDE.

### 5. Endereço da API

A aplicação utiliza a seguinte configuração:

```text
http://127.0.0.1:2026
```

---

## Testes

O projeto utiliza ferramentas de testes do ecossistema Spring e Java.

Para executar os testes:

```powershell
.\mvnw.cmd test
```

Para realizar a verificação completa do projeto:

```powershell
.\mvnw.cmd clean verify
```

Os testes ajudam a verificar o comportamento da aplicação e reduzir possíveis erros durante o desenvolvimento.

---

## Banco de dados

A aplicação foi desenvolvida sem depender de MySQL ou de um banco de dados externo.

O monitoramento é realizado por meio da consulta direta às informações disponibilizadas pelo sistema operacional e pela biblioteca OSHI.

Os usuários de autenticação são configurados em memória e suas credenciais são carregadas por variáveis de ambiente.

---

## Segurança

Algumas medidas implementadas no projeto:

- Autenticação JWT.
- Senhas armazenadas utilizando BCrypt.
- Autorização baseada em roles.
- Proteção de endpoints administrativos.
- Sessões stateless.
- Tratamento personalizado de erros.
- Limitação de tentativas de login.
- Segredos configurados por variáveis de ambiente.

Como a aplicação possui recursos capazes de executar ações no computador, o acesso deve permanecer restrito e protegido.

---

## Objetivos de aprendizado

Este projeto faz parte da minha evolução como estudante de Engenharia de Software e desenvolvedor Backend Java.

Durante seu desenvolvimento, foram trabalhados conceitos importantes, como:

- Desenvolvimento de APIs REST.
- Programação orientada a objetos.
- Arquitetura em camadas.
- Injeção de dependências.
- DTOs.
- Tratamento global de exceções.
- Validação de dados.
- Spring Security.
- Autenticação e autorização com JWT.
- Testes automatizados.
- Integração com bibliotecas externas.
- Versionamento com Git e GitHub.

---

## Desenvolvedor

**Yuri Oliveira Santos**

Estudante de Engenharia de Software e desenvolvedor Backend Java em formação.

Tenho interesse em desenvolvimento de sistemas, APIs REST, Java, Spring Boot e tecnologias voltadas à construção de soluções eficientes e seguras.

GitHub: https://github.com/yurisantosxduveen

---

Se este projeto chamou sua atenção, fique à vontade para explorar meu repositório!