# Documentação Funcional - Sprint 4

## US-13: Resolver Testes Unitários
- **Descrição:** Garantir que os testes unitários do `ProductService` passem.
- **Validação:** Pendente `mvn test` devido ao erro `Cannot resolve symbol 'BeforeEach'`.
- **Status:** Em andamento.

## US-12: Buscar Produto por ID (Opcional)
- **Descrição:** Permite buscar um produto por ID.
- **Endpoint:** GET /api/products/{id}
- **Exemplo de Requisição:** GET /api/products/1
- **Resposta:**
  ```json
  {
    "id": 1,
    "name": "Smartphone",
    "description": null,
    "price": 999.99,
    "stock": 50,
    "categories": [{"id": 1, "name": "Electronics", "description": null}]
  }
  ```
- **Validação:** Pendente.
- **Status:** Aguardando prioridade.