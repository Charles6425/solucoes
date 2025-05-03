# Projeto Spring Boot com Arquitetura Hexagonal

Este projeto implementa um sistema de controle de lançamentos financeiros utilizando **Spring Boot** e seguindo os princípios da **Arquitetura Hexagonal (Ports and Adapters)**.

---

## 🧱 Visão Geral da Arquitetura

A arquitetura hexagonal tem como objetivo isolar o núcleo da aplicação (domínio e regras de negócio) das tecnologias externas (como banco de dados, frameworks, APIs, etc). Isso facilita testes, manutenção e evolução do sistema.

---


## 📦 Estrutura de Pacotes

- **com.arquiteto.solucoes**
  - **adapters**
    - `in`: REST Controllers
    - `out`: Repositórios JPA, integrações externas
  - **application**
    - `service`: Casos de uso (regras de negócio)
    - `port`: Interfaces de entrada e saída
      - `out`: Interfaces de saída (repositórios, APIs externas)
  - **config**: Configurações (Swagger, etc.)
  - **domain**
    - `model`: Entidades do domínio (*Lancamento*, *SaldoDiario*)
    - `ports`
      - `in`: Interfaces de entrada (casos de uso)
      - `out`: Interfaces de saída (repositórios, APIs externas)
  - **enums**: Tipos como *TipoLancamento*
  - **security**: Configurações de segurança (JWT)
    - `config`:
    - `jwt`: Configurações de autenticação e autorização
    - `model`: Modelos de autenticação
    - `service`: Serviços de autenticação
  - `Application.java`: Classe principal

## 🧩 Domínios Funcionais e Capacidades

| Domínio Funcional         | Capacidade de Negócio                  |
|---------------------------|----------------------------------------|
| Lançamentos Financeiros   | Registrar transações                   |
| Consolidação Financeira   | Calcular e disponibilizar saldo diário |
| Segurança e Acesso        | Autenticar e autorizar usuários        |
| Observabilidade           | Monitorar e gerar alertas              |

---

## ✅ Requisitos Funcionais

- Criar, listar, atualizar e excluir lançamentos
- Consolidar saldo diário com base nos lançamentos
- Expor APIs REST para consumo externo

---

## 📈 Requisitos Não Funcionais

- O serviço de lançamentos deve funcionar mesmo se o serviço de consolidação estiver fora
- O serviço de consolidação deve suportar até **50 requisições por segundo**, com no máximo **5% de perda**
- Segurança com JWT
- Monitoramento com Spring Boot Actuator (ou Prometheus/Grafana)
- Documentação com Swagger

---

## 🔐 Segurança

- Autenticação via JWT
- Autorização baseada em roles (RBAC)
- Endpoints públicos: `/api/auth/**`, `/swagger-ui/**`, `/h2-console/**`
- HTTPS recomendado em produção

---

## 🔄 Integração e Comunicação

- REST entre serviços
- Possibilidade futura de usar mensageria (Kafka/RabbitMQ) para desacoplamento

---

## 🧪 Testes

- Testes unitários para serviços de domínio
- Testes de integração para endpoints REST
- Testes manuais via Swagger

---

## 📊 Monitoramento e Observabilidade (Diferencial)

- Spring Boot Actuator para métricas básicas
- Integração com Prometheus e Grafana (sugestão futura)

---

## 🔄 Arquitetura de Transição (Diferencial)

Caso exista um sistema legado:
1. Criar adaptadores para consumir dados do legado
2. Migrar gradualmente os dados para o novo sistema
3. Desativar o legado após estabilização

---

## 💰 Estimativa de Custos (Diferencial)

| Item                     | Custo Estimado (mensal) |
|--------------------------|--------------------------|
| AWS EC2 (1 instância t3) | ~\$10                    |
| RDS PostgreSQL (db.t3)   | ~\$15                    |
| S3 (backups e logs)      | ~\$1                     |
| Total                    | ~\$26                    |

---

## 🛠️ Tecnologias Utilizadas

- Java 17 + Spring Boot
- Spring Security + JWT
- Spring Data JPA + H2
- Swagger (Springdoc OpenAPI)
- Arquitetura Hexagonal
