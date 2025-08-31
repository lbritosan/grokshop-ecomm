# Plano da Sprint 5 - GrokShop Ecommerce

**Período:** Setembro 2025  
**Objetivo:** Documentar a API RESTful do GrokShop usando Swagger UI para facilitar testes e visualização dos endpoints.  
**Duração:** 1 semana  
**Time:** Leonardo Britosan (@lbritosan) - Desenvolvedor, Scrum Master, Analista Funcional  
**Repositório:** https://github.com/lbritosan/grokshop-ecomm  
**Branch:** `feature/sprint5-swagger`

## Objetivos
- Implementar documentação da API com Springdoc OpenAPI e Swagger UI (US-14).
- Documentar todos os endpoints do `ProductController` (POST, PUT, DELETE, GET por ID, GET por categoria).
- Liberar acesso ao Swagger UI sem autenticação.
- Validar endpoints via Swagger UI com autenticação HTTP Basic.
- Atualizar documentação técnica, funcional, e README.md.
- Resolver pendência da Sprint 4: aviso do Mockito (`inline-mock-maker`).

## User Stories

### US-14: Adicionar Documentação da API com Swagger
- **Descrição:** Como desenvolvedor, quero documentar a API com Swagger UI para facilitar testes e visualização dos endpoints.
- **Critérios de Aceitação:**
  - Swagger UI acessível em `http://localhost:8080/swagger-ui.html` sem autenticação.
  - Todos os endpoints do `ProductController` documentados:
    - `POST /api/products`
    - `PUT /api/products/{id}`
    - `DELETE /api/products/{id}`
    - `GET /api/products/{id}`
    - `GET /api/products/category/{categoryName}`
  - Endpoints autenticados requerem HTTP Basic (usuário: `admin`, senha: `password`).
  - Testes via Swagger UI retornam respostas esperadas (ex.: 200 OK, 404 Not Found, 401 Unauthorized).
  - Documentação OpenAPI JSON disponível em `http://localhost:8080/api-docs`.
- **Tarefas:**
  - Adicionar dependência `springdoc-openapi-starter-webmvc-ui` ao `pom.xml`.
  - Configurar `application.properties` com endpoints `/api-docs` e `/swagger-ui.html`.
  - Liberar endpoints `/api-docs` e `/swagger-ui.html` no `SecurityConfig`.
  - Anotar `ProductController` com `@Operation`, `@ApiResponses`, e `@SecurityRequirement`.
  - Testar Swagger UI em `http://localhost:8080/swagger-ui.html`.
  - Validar endpoints via Swagger UI com autenticação HTTP Basic.
  - Atualizar `docs/tecnico_sprint5.md`, `docs/funcional_sprint5.md`, e `README.md`.
  - Investigar e resolver aviso do Mockito (`inline-mock-maker`).
- **Estimativa:** 8 horas
- **Status:** To Do

## Planejamento
- **Reuniões:**
  - Sprint Planning: Definir escopo (US-14), tarefas, e estimativas.
  - Daily Standups: Acompanhar progresso e identificar impedimentos.
  - Sprint Review: Validar Swagger UI e testes dos endpoints.
  - Sprint Retrospective: Avaliar processo e planejar melhorias.
- **Ferramentas:**
  - IntelliJ IDEA 2025.2
  - Maven (3.9.6 ou superior)
  - Java 21.0.6
  - GitHub Issues e Kanban
  - Swagger UI para testes
- **Riscos:**
  - Conflitos de dependências com `springdoc-openapi`.
  - Problemas de autenticação no Swagger UI.
  - Aviso do Mockito persistente.
- **Mitigações:**
  - Limpar cache do Maven (`mvn clean install -U`).
  - Configurar `SecurityConfig` corretamente para liberar endpoints do Swagger.
  - Executar `mvn test -X` e `mvn test -Djdk.instrument.traceUsage` para depurar o Mockito.

## Pendências da Sprint 4
- Resolver aviso do Mockito (`inline-mock-maker`) nos testes unitários.
- Compartilhar saídas de `mvn test -X`, `mvn clean install -U`, `mvn test -Djdk.instrument.traceUsage`.