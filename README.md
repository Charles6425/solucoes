# 🚀 Guia Rápido de Execução do Projeto

Projeto Java com Spring Boot, seguindo o padrão de Arquitetura Hexagonal e utilizando Resilience4j para resiliência. O sistema controla lançamentos financeiros, consolida saldo diário e oferece segurança via JWT.

- Leia também o arquivo README_ARQUITETURA.md para entender a arquitetura do projeto.
- Existem também os arquivos DESENHO_DA_SOLUCAO.md e DESENHO_ARQUITETURA_TRANSICAO.md
  que mostram o desenho da solução e o desenho da arquitetura de transição, respectivamente.

## ✅ Requisitos

- Java 17 ou superior
- Maven
- IDE IntelliJ IDEA (recomendado)

## 📦 Estrutura do Projeto

A estrutura segue os princípios de Arquitetura Hexagonal com os seguintes pacotes principais:

- `com.arquiteto.solucoes.adapters`  
  Interfaces REST e repositórios JPA.

- `com.arquiteto.solucoes.application`  
  Casos de uso e regras de negócio.

- `com.arquiteto.solucoes.domain`  
  Modelos e ports (interfaces).

- `com.arquiteto.solucoes.security`  
  Implementação de autenticação e autorização com JWT.

## 🛠️ Como Rodar a Aplicação Localmente

1. **Clone o repositório**
   ```bash
   git clone <https://github.com/Charles6425/solucoes.git>



## 🧭 Abrindo o Projeto no IntelliJ IDEA

1. Selecione **File > Open** e escolha a pasta raiz do projeto.
2. Aguarde o IntelliJ indexar o projeto e baixar as dependências via **Maven**.

---

## 🗄️ Configuração do Banco de Dados e Propriedades

- O projeto utiliza o banco de dados **H2 em memória**.
- As configurações estão em:  
  `src/main/resources/application.properties`
- Para acessar a console H2, abra:  
  http://localhost:8080/h2-console
- usário: `sa`
- senha: `Solucoes@2025`

---

## ▶️ Executando a Aplicação

- Rode a classe `SolucoesApplication`, localizada em:  
  `src/main/java/com/arquiteto/solucoes/SolucoesApplication.java`
- Ou execute via terminal com:

```bash
mvn spring-boot:run
```
---

## 📚 Endpoints e Documentação
### Documentação Swagger:
- http://localhost:8080/swagger-ui/index.html
- No endpoint `api/auth/login`, utilize o seguinte corpo de requisição:
```json
{
  "username": "admin",
  "password": "minhaidentificacaodesegurancaeadmin123456789012345678901234567890"
}
```
- Após autenticação, copie o token JWT gerado e cole no campo "Authorization" do Swagger, no formato:



### Endpoint público para autenticação:

- **http://localhost:8080/api/auth/login**
- Método: `POST`
- Body:
```json
{
  "username": "admin",
  "password": "minhaidentificacaodesegurancaeadmin123456789012345678901234567890"
}
```
---