## Papel do Mentor Técnico
Como **Mentor Técnico**, meu objetivo será explicar cada detalhe técnico das implementações, cobrindo:

- **Conceitos de Java**: Por que usamos classes, interfaces, enums, coleções, exceções personalizadas, etc.
- **Conceitos de Spring**: Como o Spring Boot gerencia dependências, o papel do Hibernate/JPA, injeção de dependências, configurações como SecurityConfig.
- **Boas Práticas**: Estrutura de pacotes, padrões de projeto (ex: Repository, DTOs), e por que escolhemos certas abordagens.
- **Por que Fazemos Assim**: Explicarei o motivo de cada configuração ou código, conectando com os requisitos funcionais e não-funcionais.

Por exemplo, aqui está uma explicação técnica do que fizemos no Sprint 1:

### Explicação Técnica do Sprint 1 (Mentor Técnico)

1. **Entidades JPA (Product, Category, User, Order, CartItem):**
    - **Por que usamos JPA/Hibernate?** 
        O Java Persistence API (JPA) é um padrão para mapeamento objeto-relacional (ORM). Ele permite mapear classes Java (como Product) para tabelas no banco (como products) sem escrever SQL manualmente. O Hibernate é a implementação do JPA usada pelo Spring Data JPA.
    - **Anotações JPA**:
      - **@Entity**: Marca a classe como uma entidade mapeada para uma tabela.
      - **@Table(name = "categories")**: Define o nome da tabela no banco (ex: categories para Category).
      - **@Id e @GeneratedValue**: Define a chave primária com geração automática de IDs.
      - **@ManyToMany (em Product e Category)**: Cria uma tabela de junção (product_category) para relacionar produtos e categorias. Usamos Set para evitar duplicatas.
      - **@OneToMany (em User e Order)**: Um usuário pode ter vários pedidos, com cascade para gerenciar dependências.
   - **Lombok (@Data)**: Reduz boilerplate (getters, setters, toString, etc.) para manter o código limpo.
   - **Por que usamos enums (Role, OrderStatus)?** Enums garantem valores fixos e tipagem forte, evitando erros como strings inválidas (ex: "PENDENTE" vs "pendente").

2. **Repositórios (Spring Data JPA)**:
     - **Por que usamos JpaRepository?** O JpaRepository fornece métodos CRUD prontos (ex: **save**, **findById**, **delete**) e permite queries customizadas 
   (ex: **findByCategories_Name**). Isso reduz código boilerplate e abstrai o acesso ao banco.
     - **Exemplo**: __ProductRepository__ herda **JpaRepository<Product, Long>**, onde **Product** é a entidade e **Long** é o tipo do ID.   
3. **Spring Security e H2 Console:**
    - **Por que o redirecionamento ocorreu?** O spring-boot-starter-security ativa autenticação para todos os endpoints por padrão. 
    Sem configuração, ele redireciona para **/login**.
    - **SecurityConfig.java:**
        *  **.permitAll("/h2-console/**")**: Libera o H2 console para acesso público.
        * **.csrf().ignoringRequestMatchers("/h2-console/**")**: Desativa CSRF para o H2, já que o console usa POSTs internos.
        * **.headers().frameOptions().sameOrigin()**: Permite iframes do H2, que usa uma interface baseada em frames.
    - **Por que isso é importante?** Durante o desenvolvimento, queremos acesso fácil ao H2 console. No Sprint 4, implementaremos segurança real com JWT e roles.