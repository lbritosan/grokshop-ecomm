# Plano do Sprint 3 - GrokShop

## Objetivo do Sprint
Implementar as histórias de usuário US-05 (Atualizar Produto) e US-11 (Deletar Produto), adicionando endpoints RESTful para atualizar e deletar produtos, com testes manuais via `curl`, validação no H2 console, testes unitários e documentação técnica/funcional.

## Duração
- **Início:** 28/08/2025
- **Fim:** 04/09/2025
- **Duração:** 1 semana

## Histórias de Usuário
### US-05: Atualizar Produto
- **Descrição:** Permitir a atualização de um produto existente, alterando nome, descrição, preço, estoque e categorias associadas.
- **Critérios de Aceitação:**
  - Endpoint PUT `/api/products/{id}` atualiza o produto com base no `ProductDTO`.
  - Retorna 200 OK com o produto atualizado em JSON.
  - Validação no H2 console confirma as alterações.
  - Lança `ResourceNotFoundException` para ID inválido.
- **Tarefas:**
  - Implementar método `updateProduct` no `ProductService`.
  - Adicionar endpoint PUT no `ProductController`.
  - Testar com `curl` e validar no H2 console.
  - Escrever testes unitários.

### US-11: Deletar Produto
- **Descrição:** Permitir a deleção de um produto pelo ID.
- **Critérios de Aceitação:**
  - Endpoint DELETE `/api/products/{id}` remove o produto.
  - Retorna 204 No Content.
  - Validação no H2 console confirma a remoção.
  - Lança `ResourceNotFoundException` para ID inválido.
- **Tarefas:**
  - Implementar método `deleteProduct` no `ProductService`.
  - Adicionar endpoint DELETE no `ProductController`.
  - Testar com `curl` e validar no H2 console.
  - Escrever testes unitários.

## Tarefas do Sprint
1. **Implementar US-05: Atualizar Produto**
   - Atualizar `ProductService` com método `updateProduct`.
   - Adicionar endpoint PUT `/api/products/{id}` no `ProductController`.
2. **Implementar US-11: Deletar Produto**
   - Atualizar `ProductService` com método `deleteProduct`.
   - Adicionar endpoint DELETE `/api/products/{id}` no `ProductController`.
3. **Testar Endpoints**
   - Testar PUT e DELETE com `curl`.
   - Validar alterações no H2 console (tabelas `products`, `product_category`, `categories`).
4. **Escrever Testes Unitários**
   - Criar `ProductServiceTest` com testes para `updateProduct` e `deleteProduct` usando Mockito.
5. **Documentação**
   - Criar `docs/tecnico_sprint3.md` com detalhes técnicos.
   - Criar `docs/funcional_sprint3.md` com descrição funcional.
6. **Criar Pull Request**
   - Commit das alterações na branch `feature/sprint3-logica`.
   - Criar PR para a branch `main` no GitHub.

## Impedimentos Identificados
- **ResourceNotFoundException** no endpoint PUT `/api/products/2` devido à ausência do produto (ID 2) ou categoria (ID 2) no banco. Solução: Verificar e recriar dados no H2 console.

## Ferramentas
- **Repositório:** https://github.com/lbritosan/grokshop-ecomm
- **Banco:** H2 Database (jdbc:h2:mem:grokshop)
- **Testes:** `curl` para testes manuais, JUnit/Mockito para testes unitários
- **Logs:** `target/spring.log` para depuração

## Critérios de Conclusão
- Endpoints PUT e DELETE funcionando com respostas corretas (200 OK e 204 No Content).
- Dados validados no H2 console.
- Testes unitários passando (`mvn test`).
- Documentação técnica e funcional completa.
- PR criado e revisado no GitHub.