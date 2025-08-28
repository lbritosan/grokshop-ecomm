## Plano do Sprint 2: Lógica de Negócios e API REST

**Objetivo:** Implementar services para gerenciar produtos e categorias, com validações, e criar endpoints REST iniciais para suportar 
as user stories US-02 (navegar produtos/categorias) e US-05 (gerenciar produtos como admin).

**Duração:** 3-4 dias (ajusto ao seu ritmo; me avise se precisar de mais/menos tempo).

### Tópicos Cobertos:

* **Java:** Lambdas, streams (filtrar produtos), Optionals (evitar nulls), exceções personalizadas, coleções (Set para categorias).
* **Spring:** @Service, @Transactional, @RestController, Bean Validation (@NotNull, @Positive), Spring Data JPA queries.

### Histórias do Sprint (como Product Owner):

* **US-02:** Como cliente, quero navegar por categorias de produtos e visualizar detalhes para encontrar itens de interesse.
* **US-05:** Como administrador, quero gerenciar produtos (CRUD) para atualizar o estoque.