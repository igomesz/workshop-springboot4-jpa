# Workshop Spring Boot 4 + JPA

API REST de um sistema de pedidos (e-commerce), desenvolvida durante o curso **Java COMPLETO** do professor Nélio Alves. O projeto está publicado em produção no Northflank, com banco de dados PostgreSQL.

## Tecnologias

- Java 25
- Spring Boot 4
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Docker
- Northflank (deploy)

## Modelo de domínio

O sistema trabalha com seis entidades:

| Entidade    | Descrição                                              |
|-------------|--------------------------------------------------------|
| User        | Cliente que faz os pedidos                             |
| Order       | Pedido, com data e status                              |
| OrderItem   | Item de um pedido (produto, quantidade e preço)        |
| Payment     | Pagamento associado a um pedido                        |
| Product     | Produto à venda                                        |
| Category    | Categoria de produtos (um produto pode ter várias)     |

Tabelas no banco: `tb_user`, `tb_order`, `tb_order_item`, `tb_payment`, `tb_product`, `tb_category` e `tb_product_category`.

## Endpoints

| Recurso      | Caminho       |
|--------------|---------------|
| Usuários     | `/users`      |
| Produtos     | `/products`   |
| Categorias   | `/categories` |
| Pedidos      | `/orders`     |

Exemplo: `GET /users` retorna a lista de usuários em JSON. O recurso de usuários também aceita inserção, atualização e exclusão, com tratamento de exceções que devolve respostas de erro padronizadas.

## Arquitetura

O projeto segue a organização em camadas:

```
resources  ->  services  ->  repositories  ->  banco de dados
(controllers)   (regras)      (Spring Data JPA)
```

- `resources`: controladores REST e o tratador de exceções (`ResourceExceptionHandler`)
- `services`: regras de negócio
- `repositories`: acesso aos dados com Spring Data JPA
- `entities`: modelo de domínio

## Perfis de execução

| Perfil | Uso                                   |
|--------|---------------------------------------|
| test   | Testes                                |
| dev    | Desenvolvimento e produção (PostgreSQL) |

O perfil é escolhido pela variável `SPRING_PROFILES_ACTIVE`.

## Como rodar localmente

**Requisitos:** Java 25, Maven (ou o `mvnw` do projeto) e PostgreSQL.

1. Clone o repositório:

   ```bash
   git clone https://github.com/igomesz/workshop-springboot4-jpa.git
   cd workshop-springboot4-jpa
   ```

2. Crie um banco chamado `springboot_course` no PostgreSQL local.

3. Configure o usuário e a senha do seu PostgreSQL no arquivo `src/main/resources/application-dev.properties`, ou use as variáveis de ambiente descritas abaixo.

4. Execute o projeto:

   ```bash
   ./mvnw spring-boot:run
   ```

5. Acesse `http://localhost:8080/users`.

## Variáveis de ambiente

O `application-dev.properties` lê a conexão com o banco por variáveis de ambiente. Se elas não existirem, usa os valores locais padrão.

| Variável                | Descrição                                  |
|-------------------------|--------------------------------------------|
| `DB_URL`                | URL JDBC do banco                          |
| `DB_USER`               | Usuário do banco                           |
| `DB_PASSWORD`           | Senha do banco                             |
| `SPRING_PROFILES_ACTIVE`| Perfil ativo (`dev`)                       |

## Docker

O `Dockerfile` compila o projeto com Maven e executa o `.jar` na porta 8080:

```bash
docker build -t workshop-springboot .
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=dev \
  -e DB_URL=jdbc:postgresql://host:5432/banco \
  -e DB_USER=usuario \
  -e DB_PASSWORD=senha \
  workshop-springboot
```

## Deploy

O deploy é feito no **Northflank**, com integração contínua ligada ao GitHub:

1. Um `git push` na branch `main` dispara um build automático a partir do `Dockerfile`.
2. O Northflank publica a nova versão assim que o build termina.
3. O app se conecta ao banco PostgreSQL do próprio Northflank pela rede interna, usando as variáveis de ambiente.

## Autor

**Pedro Gomes**

GitHub: [@igomesz](https://github.com/igomesz)
