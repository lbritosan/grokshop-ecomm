# GrokShop - E-commerce API

## Visão Geral

GrokShop é um projeto de API RESTful para um sistema de e-commerce, desenvolvido como parte de um 
exercício prático para implementar funcionalidades de gerenciamento de produtos e categorias. 
O objetivo é criar uma aplicação backend robusta que permita criar, atualizar, deletar e listar 
produtos, com suporte a associações com categorias, utilizando Spring Boot e um banco de dados em 
memória (H2).

O projeto segue uma abordagem ágil, com sprints semanais, e inclui documentação técnica e funcional, 
testes manuais via `curl`, e validação no H2 console. Futuramente, testes unitários serão 
adicionados para garantir a qualidade do código.

## Funcionalidades Implementadas

- **Sprint 1:** Configuração inicial do projeto com Spring Boot, JPA, H2 Database, e Lombok.
- **[Sprint 2](src/main/resources/docs/sprint2/SPRINT_2.md) :**
  - **US-01:** Criar produto (POST `/api/products`).
  - **US-02:** Listar produtos por categoria (GET `/api/products/category/{categoryName}`).
  - Resolvidos erros de serialização (`HttpMessageNotWritableException`) e concorrência (`ConcurrentModificationException`).
- **[Sprint 3](src/main/resources/docs/sprint3/SPRINT_3.md) :**
  - **US-05:** Atualizar produto (PUT `/api/products/{id}`).
  - **US-11:** Deletar produto (DELETE `/api/products/{id}`).
  - Resolvido `ResourceNotFoundException` garantindo dados no banco.
- **[Sprint 4](src/main/resources/docs/sprint4/SPRINT_4.md):**
  - **US-12:** Endpoint GET `/api/products/{id}` com autenticação HTTP Basic (usuário: `admin`, senha: `password`).
  - **US-13:** Testes unitários para `ProductService` com 100% de cobertura (8/8 testes passando).
- **[Sprint 5](src/main/resources/docs/sprint5/SPRINT_5.md):**
    - **US-14**

## Tecnologias Utilizadas

- **Java:** 21.0.6 LTS
- **Spring Boot:** 3.3.2
- **Spring Data JPA:** Para mapeamento objeto-relacional
- **H2 Database:** Banco de dados em memória para desenvolvimento
- **Lombok:** Para redução de código boilerplate
- **Maven:** Gerenciamento de dependências e build
- **JUnit 5 e Mockito:** Para testes unitários (pendente)
- **GitHub:** Controle de versão e repositório

## Estrutura do Projeto

- **src/main/java/com/grokshop**: Código-fonte principal
  - **controller**: Controladores REST (`ProductController`)
  - **service**: Lógica de negócio (`ProductService`)
  - **entity**: Entidades JPA (`Product`, `Category`)
  - **repository**: Repositórios JPA (`ProductRepository`, `CategoryRepository`)
  - **dto**: Objetos de transferência (`ProductDTO`)
  - **exception**: Exceções customizadas (`ResourceNotFoundException`)
- **src/main/resources**:
  - `application.properties`: Configurações do Spring Boot e H2
  - `data.sql`: Script inicial para popular o banco
- **docs**:
  - `plano_sprintX.md`: Planejamento de cada sprint
  - `tecnico_sprintX.md`: Documentação técnica
  - `funcional_sprintX.md`: Documentação funcional

## Configuração e Execução

### Pré-requisitos
- Java 21.0.6 LTS
- Maven 3.9.x
- IDE (ex: IntelliJ IDEA) com suporte a Lombok

### Passos para Executar
1. **Clonar o Repositório:**
   ```bash
   git clone https://github.com/lbritosan/grokshop-ecomm.git
   cd grokshop-ecomm
   ```
2. **Instalar Dependências:**
   ```bash
   mvn clean install
   ```
3. **Executar a Aplicação:**
   ```bash
   mvn spring-boot:run
   ```
4. **Acessar o H2 Console:**
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:grokshop`
   - Usuário: `sa`
   - Senha: vazio

### Testar Endpoints
Use `curl` para testar os endpoints:
- **Criar Produto:**
  ```bash
  curl -v -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d "{\"name\": \"Laptop\", \"description\": \"High-end laptop\", \"price\": 1499.99, \"stock\": 20, \"categoryIds\": [1]}"
  ```
- **Atualizar Produto:**
  ```bash
  curl -v -X PUT http://localhost:8080/api/products/2 -H "Content-Type: application/json" -d "{\"name\": \"Laptop Pro\", \"description\": \"High-end laptop upgraded\", \"price\": 1599.99, \"stock\": 25, \"categoryIds\": [1, 2]}"
  ```
- **Deletar Produto:**
  ```bash
  curl -v -X DELETE http://localhost:8080/api/products/2
  ```
- **Listar Produtos por Categoria:**
  ```bash
  curl -v -X GET http://localhost:8080/api/products/category/Electronics
  ```

## Próximos Passos
- **Sprint 4 (Planejamento):**
  - Implementar testes unitários pendentes para `ProductService` (resolvendo `NoSuchMethodError` do JUnit 5).
  - Adicionar novas funcionalidades, como busca por ID de produto ou filtros avançados.
- **Melhorias:**
  - Adicionar validações de entrada no `ProductDTO`.
  - Implementar autenticação e autorização.
  - Configurar CI/CD no GitHub Actions.

## Contato
- **Repositório:** https://github.com/lbritosan/grokshop-ecomm
- **Responsável:** Leonardo Brito dos Santos (@lbritosan)