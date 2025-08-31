# Documento Técnico - GrokShop (Sprint 5 - US-14)

## Arquitetura
- **Framework:** Spring Boot 3.5.5.
- **Linguagem:** Java 21.0.6.
- **Banco de Dados:** H2 2.3.232 (in-memory, `jdbc:h2:mem:testdb`).
- **Autenticação:** Spring Security com HTTP Basic (`admin:password`).
- **Documentação:** Springdoc OpenAPI 2.6.0 para Swagger UI.
- **Testes:** JUnit 5 e Mockito (adiados para o final do projeto).

## Configurações Implementadas na Sprint 5
1. **Springdoc OpenAPI:**
    - Dependência adicionada ao `pom.xml`:
      ```xml
      <dependency>
          <groupId>org.springdoc</groupId>
          <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
          <version>2.6.0</version>
      </dependency>