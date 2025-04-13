# Sistema de Gerenciamento de RPG

Sistema desenvolvido para a atividade individual de CRUD, com foco na aplicação dos conceitos de Programação Orientada a Objetos (POO). O projeto permite o gerenciamento de **Personagens** e **Itens Mágicos** para um jogo de RPG.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Banco de dados H2 (em memória)
- Maven

## Descrição do Projeto

A aplicação permite:

- Cadastrar personagens com nome, nome aventureiro, classe, level, força e defesa.
- Adicionar itens mágicos aos personagens.
- Visualizar e buscar personagens e seus itens.
- Atualizar o nome aventureiro do personagem.
- Remover personagens e itens.

### Regras principais

#### Personagem

- Deve distribuir até **10 pontos** entre força e defesa.
- Pode ter vários itens mágicos.
- Só pode ter **um item do tipo AMULETO**.
- Os valores de força e defesa exibidos consideram os bônus dos itens.

#### Item Mágico

- Tipos: **ARMA**, **ARMADURA**, **AMULETO**.
- ARMA: defesa = 0 obrigatoriamente.
- ARMADURA: força = 0 obrigatoriamente.
- AMULETO: pode ter força e defesa.
- Força e defesa no item: **máximo 10 cada**.
- Não pode ter força = 0 **e** defesa = 0.

## 🔗 Rotas disponíveis

### Personagem

- `POST /personagens` - Cadastrar personagem
- `GET /personagens` - Listar personagens
- `GET /personagens/{id}` - Buscar personagem por ID
- `PUT /personagens/{id}/nome-aventureiro` - Atualizar nome aventureiro
- `DELETE /personagens/{id}` - Remover personagem

### Item Mágico

- `POST /personagens/{id}/itens` - Adicionar item ao personagem
- `GET /personagens/{id}/itens` - Listar itens do personagem
- `DELETE /personagens/itens/{itemId}` - Remover item
- `GET /personagens/{id}/amuleto` - Verificar se o personagem tem amuleto

##  H2 Console

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuário: `sa`
- Senha: *(em branco)*

---
