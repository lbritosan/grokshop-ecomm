# Documento Funcional - Sprint 2: Lógica de Negócios e API REST

## Objetivo
Implementar a lógica de negócios para gerenciamento de produtos e categorias, com endpoints REST para navegação e administração.

## User Stories
- **US-02:** Como cliente, quero navegar por categorias de produtos e visualizar detalhes para encontrar itens de interesse.
- **US-05:** Como administrador, quero gerenciar produtos (CRUD) para atualizar o estoque.

## Funcionalidades
- **Criar Produto:** Permite criar um produto com nome, descrição, preço, estoque e categorias associadas.
- **Listar Produtos por Categoria:** Retorna produtos filtrados por nome da categoria.
- **Validações:**
    - Nome: Não nulo, não vazio.
    - Preço e estoque: Positivos.
    - Categorias: IDs válidos.
- **Endpoints:**
    - `POST /api/products`: Cria um produto.
    - `GET /api/products/category/{categoryName}`: Lista produtos por categoria.

## US-01: Criar Produto
- **Descrição:** Permite criar um produto com nome, descrição, preço, estoque e categorias associadas.
- **Endpoint:** POST /api/products
- **Exemplo de Requisição:**
  ```json
  {
    "name": "Laptop",
    "description": "High-end laptop",
    "price": 1499.99,
    "stock": 20,
    "categoryIds": [1]
  }
  ```
- Resposta: 200 OK com o produto criado, incluindo ID e categorias.
- Validação: Produto aparece no H2 console e no GET /api/products/category/Electronics.

## US-02: Listar Produtos por Categoria
- Descrição: Lista todos os produtos associados a uma categoria pelo nome.
- Endpoint: GET /api/products/category/{categoryName}
- Exemplo: GET /api/products/category/Electronics
- Resposta: 200 OK com lista de produtos.


## Requisitos Não-Funcionais
- **Performance:** Respostas < 200ms.
- **Segurança:** Endpoints protegidos (a implementar no Sprint 4).
- **Logs:** Registrar erros (SLF4J configurado automaticamente).

## Fluxos
- **Criar Produto:** Admin envia JSON com dados do produto → Validação → Persistência no banco → Retorna produto criado.
- **Listar por Categoria:** Cliente acessa `/api/products/category/Electronics` → Retorna lista de produtos.
- **CategoryRepository:** Interface JpaRepository para a entidade Category, com método customizado `findByName`. Usado para buscar categorias por ID no ProductService e potencialmente por nome em funcionalidades futuras.

## Próximos Passos
- Adicionar endpoints para atualização/exclusão de produtos (Sprint 3).
- Implementar segurança com JWT (Sprint 4).