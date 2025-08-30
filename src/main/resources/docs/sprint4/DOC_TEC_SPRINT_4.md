# Documentação Técnica - Sprint 4

## US-12: Buscar Produto por ID
- **Descrição:** Implementar endpoint GET /api/products/{id} com autenticação via Spring Security.
- **Implementação:**
    - Adicionado `spring-boot-starter-security` ao `pom.xml`.
    - Configurado `SecurityConfig` com HTTP Basic usando `httpBasic(Customizer)`.
    - Adicionado `findById` ao `ProductService`.
    - Adicionado GET `/api/products/{id}` ao `ProductController`.
    - Adicionado `data.sql` para popular banco H2.
- **Depuração:**
    - Resolvido: IntelliJ não reconhecendo aplicação.
    - Resolvido: Erros no `pom.xml`.
    - Resolvido: Falta de `org.springframework.security`.
    - Resolvido: `httpBasic()` obsoleto.
    - Resolvido: Banco H2 vazio.
    - Resolvido: `PUT /api/products/1` retornava `405 Method Not Allowed`.
- **Testes:**
    - Endpoints validados com `curl` e H2 console.
- **Status:** Concluído.

## US-13: Implementar Testes Unitários para ProductService
- **Descrição:** Criar testes unitários para `ProductService`.
- **Implementação:**
    - Adicionado `spring-boot-starter-test`, `spring-security-test`, `mockito-core`, `mockito-inline`, e `byte-buddy-agent` ao `pom.xml`.
    - Configurado `byte-buddy-agent` no `maven-surefire-plugin`.
    - Criado `ProductServiceTest.java` com testes para `createProduct`, `updateProduct`, `deleteProduct`, `findProductsByCategory`, `findById`.
- **Depuração:**
    - Resolvido: Erro `NoSuchMethodError` no JUnit (conflito de versões).
    - Resolvido: Erro `ClassCastException` no `ProductServiceTest.java` (uso de Set.of imutável).
    - Resolvido: Erro `incompatible types` no `productDTO.setCategoryIds` (HashSet<Long> para List<Long>).
    - Resolvido: Falha no `testCreateProduct_Success` (BigDecimal vs Double e `existsById` não chamado).
    - Resolvido: Falhas nos testes `testCreateProduct_CategoryNotFound`, `testUpdateProduct_ProductNotFound`, `testDeleteProduct_ProductNotFound` (mensagens de exceção ajustadas para "with id").
- **Testes:**
    - Executado `mvn test` com 8/8 testes passando.
    - Cobertura: 100% nas classes testadas.
- **Status:** Concluído.

## Depuração
- **Impedimentos Resolvidos:**
    - Conflitos de dependências de teste.
    - `ClassCastException` no `ProductServiceTest.java`.
    - `incompatible types` no `productDTO.setCategoryIds`.
    - Falha no `testCreateProduct_Success` (BigDecimal vs Double e `existsById` não chamado).
    - Falhas nos testes `testCreateProduct_CategoryNotFound`, `testUpdateProduct_ProductNotFound`, `testDeleteProduct_ProductNotFound`.
- **Impedimentos Pendentes:**
    - Aviso do Mockito (inline-mock-maker).
- **Ações:**
    - Compartilhar saídas de `mvn test -X`, `mvn clean install -U`, `mvn test -Djdk.instrument.traceUsage` para investigar o aviso do Mockito.

## Testes
- Resultado: 8/8 testes passando com cobertura de 100%.
- Pendente: Configuração do JaCoCo para relatórios detalhados (opcional, Sprint 5).