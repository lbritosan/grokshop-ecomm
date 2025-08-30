# Plano da Sprint 4 - GrokShop Ecommerce

**Período:** Agosto 2025  
**Objetivo:** Implementar o endpoint de busca de produtos por ID com autenticação e garantir a cobertura total de testes unitários para o `ProductService`.  
**Duração:** 2 semanas  
**Time:** Leonardo Britosan (@lbritosan) - Desenvolvedor, Scrum Master, Analista Funcional  
**Repositório:** https://github.com/lbritosan/grokshop-ecomm  
**Branch:** `feature/sprint4-logica`

## Objetivos
- Finalizar a implementação do endpoint GET `/api/products/{id}` com autenticação HTTP Basic (US-12).
- Criar testes unitários para todos os métodos do `ProductService` com cobertura de 100% (US-13).
- Atualizar a documentação técnica e funcional.
- Resolver impedimentos técnicos (ex.: erros de dependências, testes falhando).
- Preparar o projeto para a próxima sprint (documentação da API com Swagger).

## User Stories

### US-12: Buscar Produto por ID
- **Descrição:** Como usuário autenticado, quero buscar um produto por ID para visualizar seus detalhes.
- **Critérios de Aceitação:**
  - Endpoint GET `/api/products/{id}` implementado.
  - Autenticação via HTTP Basic (usuário: `admin`, senha: `password`).
  - Resposta 200 OK com detalhes do produto (ID, nome, descrição, preço, estoque, categorias).
  - Resposta 404 Not Found para IDs inexistentes.
  - Resposta 401 Unauthorized para acessos não autenticados.
  - Validação via `curl` e console H2.
- **Tarefas:**
  - Adicionar `spring-boot-starter-security` ao `pom.xml`.
  - Configurar `SecurityConfig` com HTTP Basic.
  - Implementar `findById` no `ProductService`.
  - Adicionar endpoint GET `/api/products/{id}` no `ProductController`.
  - Popular banco H2 com `data.sql`.
  - Validar endpoint com `curl` e H2 console.
- **Estimativa:** 8 horas
- **Status:** Concluído

### US-13: Implementar Testes Unitários para ProductService
- **Descrição:** Como desenvolvedor, quero testes unitários para o `ProductService` para garantir a qualidade do código.
- **Critérios de Aceitação:**
  - Testes unitários para `createProduct`, `updateProduct`, `deleteProduct`, `findProductsByCategory`, `findById`.
  - Cobertura de 100% nas classes testadas.
  - Todos os testes (8/8) passando com `mvn test`.
  - Mensagens de exceção consistentes (ex.: "Product not found with id").
- **Tarefas:**
  - Adicionar `spring-boot-starter-test`, `spring-security-test`, `mockito-core`, `mockito-inline`, e `byte-buddy-agent` ao `pom.xml`.
  - Configurar `byte-buddy-agent` no `maven-surefire-plugin`.
  - Criar `ProductServiceTest.java` com todos os testes.
  - Resolver erros de dependências (ex.: `NoSuchMethodError` no JUnit).
  - Corrigir `ClassCastException` e `incompatible types` no `ProductServiceTest.java`.
  - Ajustar mensagens de exceção no `ProductService`.
  - Validar testes com `mvn test`.
- **Estimativa:** 12 horas
- **Status:** Concluído

## Planejamento
- **Reuniões:**
  - Sprint Planning: Definir escopo (US-12, US-13), estimativas, e tarefas.
  - Daily Standups: Acompanhar progresso, identificar impedimentos.
  - Sprint Review: Apresentar resultados (endpoints funcionando, testes passando).
  - Sprint Retrospective: Avaliar processo e planejar melhorias para a Sprint 5.
- **Ferramentas:**
  - IntelliJ IDEA 2025.2
  - Maven (3.9.6 ou superior)
  - Java 21.0.6
  - GitHub Issues para rastreamento
  - Kanban para visualização
- **Riscos:**
  - Conflitos de dependências no `pom.xml`.
  - Falhas nos testes devido a configurações do Mockito.
  - Aviso do Mockito (`inline-mock-maker`) persistente.
- **Mitigações:**
  - Limpar cache do Maven (`mvn clean install -U`).
  - Configurar `mockito-inline` e `byte-buddy-agent` corretamente.
  - Executar `mvn test -X` e `mvn test -Djdk.instrument.traceUsage` para depuração.

## Impedimentos Resolvidos
- IntelliJ não reconhecendo aplicação.
- Erros no `pom.xml` (dependências faltando).
- `httpBasic()` obsoleto no Spring Security.
- Banco H2 vazio (resolvido com `data.sql`).
- `PUT /api/products/1` retornava `405 Method Not Allowed`.
- `NoSuchMethodError` no JUnit (conflito de versões).
- `ClassCastException` no `ProductServiceTest.java` (Set.of imutável).
- `incompatible types` no `productDTO.setCategoryIds` (HashSet<Long> para List<Long>).
- Falha no `testCreateProduct_Success` (BigDecimal vs Double, `existsById` não chamado).
- Falhas em `testCreateProduct_CategoryNotFound`, `testUpdateProduct_ProductNotFound`, `testDeleteProduct_ProductNotFound` (mensagens de exceção ajustadas).

## Pendências
- Resolver aviso do Mockito (`inline-mock-maker`) na Sprint 5.
- Compartilhar saídas de `mvn test -X`, `mvn clean install -U`, `mvn test -Djdk.instrument.traceUsage`.

## Próximos Passos
- Criar Pull Request para merge da branch `feature/sprint4-logica` na `develop`.
- Atualizar Kanban e fechar issues da Sprint 4.
- Planejar Sprint 5 com US-14 (documentação da API com Swagger).