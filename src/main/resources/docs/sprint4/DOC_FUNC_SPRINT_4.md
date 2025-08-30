# Documentação Funcional - Sprint 4

## US-12: Buscar Produto por ID
- **Descrição:** Permite buscar um produto por ID com autenticação HTTP Basic.
- **Endpoint:** GET /api/products/{id}
- **Autenticação:** Usuário: `admin`, Senha: `password`
- **Exemplo de Requisição:**
  ```bash
  curl -u admin:password -X GET http://localhost:8080/api/products/1
  ```
- **Resposta (200 OK):**
  ```json
  {
    "id": 1,
    "name": "Smartphone",
    "description": "High-end smartphone",
    "price": 999.99,
    "stock": 50,
    "categories": [{"id": 1, "name": "Electronics", "description": "Electronic products"}]
  }
  ```
- **Resposta (404 Not Found):**
  ```json
  {"message": "Product not found with id: 999"}
  ```
- **Resposta (401 Unauthorized):**
  ```json
  {"status": 401, "error": "Unauthorized"}
  ```
- **Validação:** Concluída com `curl` e H2 console.
- **Status:** Concluído.

## US-13: Implementar Testes Unitários
- **Descrição:** Criar testes unitários para `ProductService`.
- **Testes:**
    - Cobrir `createProduct`, `updateProduct`, `deleteProduct`, `findProductsByCategory`, `findById`.
    - Executar `mvn test`.
- **Impedimentos:**
    - Erro `NoSuchMethodError` no JUnit (resolvido).
    - Erro `ClassCastException` no `ProductServiceTest.java` (resolvido).
    - Erro `incompatible types` no `productDTO.setCategoryIds` (resolvido).
    - Falha no `testCreateProduct_Success` (BigDecimal vs Double e `existsById` não chamado) (resolvido).
    - Falhas nos testes `testCreateProduct_CategoryNotFound`, `testUpdateProduct_ProductNotFound`, `testDeleteProduct_ProductNotFound` (mensagens de exceção ajustadas).
- **Status:** Concluído.
- **Cobertura:** 100% nas classes testadas.