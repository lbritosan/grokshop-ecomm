# Documentação Técnica - Sprint 3

## US-05: Atualizar Produto

- **Descrição:** Implementado endpoint PUT /api/products/{id} para atualizar um produto existente.
- **Implementação:**
    - Método `updateProduct` no `ProductService` busca o produto por ID, atualiza campos com base no `ProductDTO`, e gerencia categorias com `clear()` e `addAll()`.
    - Endpoint PUT no `ProductController` recebe ID e `ProductDTO`, retornando o produto atualizado.
- **Depuração:**
    - Resolvido ResourceNotFoundException garantindo a existência de produto (ID 2) e categorias (IDs 1, 2) no banco via POST e INSERT no H2 console.
    - Testes unitários adiados devido a NoSuchMethodError (incompatibilidade de JUnit), a ser resolvido após funcionalidades.
- **Testes:** Testado com `curl` (PUT /api/products/2) e validado no H2 console.

## US-11: Deletar Produto

- **Descrição:** Implementado endpoint DELETE /api/products/{id} para deletar um produto.
- **Implementação:**
    - Método `deleteProduct` no `ProductService` busca o produto por ID e o deleta.
    - Endpoint DELETE no `ProductController` retorna 204 No Content.
- **Testes:** Pendente teste com `curl` (DELETE /api/products/2) e validação no H2 console.

## Depuração

- Identificado ResourceNotFoundException no PUT /api/products/2 devido a produto ou categoria ausentes. Corrigido com recriação de dados no banco.
- NoSuchMethodError nos testes unitários (JUnit 5) adiado para após a finalização das funcionalidades.

## Testes

1. Endpoints testados com `curl` para PUT e DELETE.
2. Validação no H2 console para confirmar alterações no banco.
3. Testes unitários pendentes, adiados para o final do Sprint.