# Documento Técnico - Sprint 2: Lógica de Negócios e API REST

## Arquitetura
- **Camadas:** Entidades (`com.grokshop.entity`), Repositórios (`com.grokshop.repository`), DTOs (`com.grokshop.dto`), Serviços (`com.grokshop.service`), Controladores (`com.grokshop.controller`).
- **Tecnologias:** Java 21, Spring Boot 3.5.5, Spring Data JPA, H2 Database, Bean Validation, Lombok, JUnit 5, Mockito, AssertJ.

## Componentes Implementados
- **CategoryRepository:** Interface JpaRepository com método `findByName`.
- **ProductDTO:** Encapsula dados de entrada/saída, com validações (`@NotBlank`, `@Positive`).
- **ProductService:** Lógica para criar produtos e buscar por categoria, com `@Transactional`.
- **ProductController:** Endpoints REST (`POST /api/products`, `GET /api/products/category/{categoryName}`).
- **Testes:** Testes unitários adiados para o final do sprint, implementados em `ProductServiceTest` com JUnit 5, Mockito, AssertJ.

## Configurações
- **pom.xml:** Dependências `spring-boot-starter-validation`, `assertj-core`.
- **application.properties:** Configuração do H2 mantida do Sprint 1.

## Decisões Técnicas
- **DTOs:** Separam API das entidades, melhorando segurança.
- **Bean Validation:** Garante dados válidos.
- **Testes Adiados:** Testes unitários movidos para o final do sprint para priorizar entrega de endpoints.

## Funcionalidade Implementada:
- Criação de produtos com associação a categorias (POST /api/products).
- **Depuração:**
    - Resolvido ConcurrentModificationException ajustando equals e hashCode nas entidades Product e Category para usar apenas id.
    - Resolvido HttpMessageNotWritableException com @JsonBackReference na coleção products de Category para evitar serialização cíclica.
- **Testes:** Endpoints testados com curl, validados no H2 console.

## Próximos Passos
- Adicionar paginação e sorting (Sprint 3).
- Implementar segurança com JWT (Sprint 4).
- Configurar caching (Sprint 5).