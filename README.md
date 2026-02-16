# Gerenciamento de Tarefas com SpringBoot | REST API's

API REST para gerenciamento de tarefas, desenvolvida com Spring Boot.
Permite o cadastro de usuários e o controle de tarefas associadas a cada conta, utilizando autenticação básica.

## Stack

* Java
* MySQL
* Spring Boot
* Spring Data JPA
* Maven
* OpenAPI (Swagger)

## Documentação

A documentação interativa da API está disponível em:
https://todolist-spring-apirest.onrender.com/swagger-ui/index.html

## Autenticação

A API utiliza **HTTP Basic Authentication**.

Fluxo de uso:

1. Criar um usuário via endpoint de cadastro.
2. Autenticar com usuário e senha.
3. Acessar os endpoints de tarefas.

## Endpoints

### Usuários

* `POST /usuarios` — cria um usuário

### Tarefas (requer autenticação)

* `GET /tarefas` — lista tarefas do usuário autenticado
* `POST /tarefas` — cria uma tarefa
* `PUT /tarefas/{id}` — atualiza uma tarefa

## Execução local

```bash
git clone https://github.com/lucasfranca13/todolist-spring-apiRest.git
cd todolist-spring-apiRest
./mvnw spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

Swagger local:

```
http://localhost:8080/swagger-ui/index.html
```
