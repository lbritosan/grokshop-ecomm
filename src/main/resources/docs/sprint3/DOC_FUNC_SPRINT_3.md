# Documentação Funcional - Sprint 3

## US-05: Atualizar Produto
- **Descrição:** Permite atualizar um produto existente com novos valores para nome, descrição, preço, estoque e categorias associadas.
- **Endpoint:** PUT /api/products/{id}
- **Exemplo de Requisição:**
  ```json
  {
    "name": "Laptop Pro",
    "description": "High-end laptop upgraded",
    "price": 1599.99,
    "stock": 25,
    "categoryIds": [1, 2]
  }
  ```
- **Resposta:** 200 OK com o produto atualizado, incluindo ID e categorias.
- **Validação:** Produto atualizado aparece no H2 console e no GET /api/products/category/{categoryName}.

## US-11: Deletar Produto
- **Descrição:** Permite deletar um produto pelo ID.
- **Endpoint:** DELETE /api/products/{id}
- **Exemplo:** DELETE /api/products/2
- **Resposta:** 204 No Content.
- **Validação:** Produto removido do H2 console e do GET /api/products/category/{categoryName}.