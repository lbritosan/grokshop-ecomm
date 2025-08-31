# Documento Funcional - GrokShop (Sprint 5 - US-14)

## Objetivo
Implementar a documentação da API REST do GrokShop usando Swagger (OpenAPI 3.0), permitindo que os endpoints do `ProductController` sejam acessíveis e testáveis via interface web, com autenticação HTTP Basic.

## Descrição da User Story (US-14)
- **Título:** Documentação da API com Swagger
- **Descrição:** Como desenvolvedor, quero documentar todos os endpoints da API REST do GrokShop usando Swagger, para facilitar o teste e a integração por outros desenvolvedores.
- **Critérios de Aceitação:**
    - O Swagger UI deve estar acessível em `http://localhost:8080/swagger-ui.html` sem autenticação.
    - Todos os endpoints do `ProductController` (`POST /api/products`, `PUT /api/products/{id}`, `DELETE /api/products/{id}`, `GET /api/products/{id}`, `GET /api/products/category/{categoryName}`) devem estar documentados com descrições claras, parâmetros, e respostas esperadas.
    - Os endpoints protegidos por autenticação HTTP Basic (`admin:password`) devem ser testáveis via Swagger UI após autenticação no botão **Authorize**.
    - Respostas de erro (400, 401, 404, 500) devem estar documentadas com mensagens claras.
    - O banco H2 deve conter dados iniciais para testes (ex.: produto com `id=1` e categoria `Electronics`).

## Funcionalidades Implementadas na Sprint 5
- Configuração do Swagger (Springdoc OpenAPI) para documentar os endpoints do `ProductController`.
- Integração com Spring Security para proteger os endpoints `/api/products/**` com autenticação HTTP Basic (`admin:password`).
- Liberação do acesso público ao Swagger UI (`/swagger-ui/**` e `/api-docs/**`) no `SecurityConfig.java`.
- Correção de erros de serialização JSON (`ConcurrentModificationException`) com anotações `@JsonManagedReference` e `@JsonBackReference` nas entidades `Product` e `Category`.
- Configuração do banco H2 com `data.sql` para inicializar produtos e categorias.
- Correção do `ProductDTO.java` para usar `Double` no campo `price`, resolvendo o `NoSuchMethodError`.

## Endpoints Documentados
1. **POST /api/products**:
    - **Descrição:** Cria um novo produto.
    - **Autenticação:** Requer `admin:password`.
    - **Corpo da Requisição:**
      ```json
      {
        "name": "Laptop",
        "description": "High-performance laptop",
        "price": 1499.99,
        "stock": 30,
        "categoryIds": [1]
      }