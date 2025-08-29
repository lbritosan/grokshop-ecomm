# Documentação Técnica - Sprint 4

## US-13: Resolver Testes Unitários
- **Descrição:** Corrigir erros `NoSuchMethodError` e `Cannot resolve symbol 'BeforeEach'` nos testes unitários do `ProductServiceTest`.
- **Implementação:**
  - Atualizado `pom.xml` com JUnit 5.10.3 e `maven-surefire-plugin` 3.2.5.
  - Validada `ProductServiceTest.java` com imports corretos.
- **Depuração:**
  - Impedimento resolvido: IntelliJ não reconhecia aplicação após atualização, corrigido clonando repositório em nova pasta.
  - Pendente: Resolução de `Cannot resolve symbol 'BeforeEach'`.
- **Testes:**
  - Pendente: Executar `mvn clean test` após correção do erro.
  - Resultados serão salvos em `target/surefire-reports/`.

## US-12: Buscar Produto por ID (Opcional)
- **Descrição:** Implementar endpoint GET /api/products/{id}.
- **Implementação:** Pendente, aguardando confirmação da prioridade.
- **Testes:** Pendente.

## Depuração
- **Impedimentos Resolvidos:**
  - IntelliJ não reconhecendo aplicação (clonado repositório em nova pasta).
- **Impedimentos Pendentes:**
  - `Cannot resolve symbol 'BeforeEach'`, investigando sincronização do Maven e cache.
- **Ações:**
  - Sincronizar `pom.xml`.
  - Verificar JDK 21.0.6 e plugins.
  - Executar `mvn clean test`.

## Testes
- Pendente: Testes unitários e manuais.
- Validação no H2 console planejada.

## Status
- Sprint 4 em andamento, focado na US-13.