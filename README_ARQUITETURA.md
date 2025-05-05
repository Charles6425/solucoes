
# Projeto Spring Boot com Arquitetura Hexagonal e Resiliência

Este projeto implementa um sistema de controle de lançamentos financeiros utilizando **Spring Boot**, seguindo os princípios da **Arquitetura Hexagonal (Ports and Adapters)**. Também incorpora mecanismos de resiliência com **Resilience4j**, garantindo operação contínua mesmo diante de falhas em integrações externas.

---

## 🧱 Visão Geral da Arquitetura

A **Arquitetura Hexagonal** isola o núcleo da aplicação (domínio e regras de negócio) das tecnologias externas (banco de dados, frameworks, APIs etc.), facilitando testes, manutenção e evolução. A **resiliência** é implementada com padrões como *Circuit Breaker* e *fallback*, assegurando funcionamento mesmo com falhas externas.

---

## 📦 Estrutura de Pacotes

- **com.arquiteto.solucoes**
  - **adapters**
    - `in`: REST Controllers
    - `out`: Repositórios JPA, integrações externas
  - **application**
    - `service`: Casos de uso (regras de negócio)
    - `port`: Interfaces de entrada e saída
  - **config**: Configurações gerais (Swagger, etc.)
  - **domain**
    - `model`: Entidades do domínio (*Lancamento*, *SaldoDiario*)
    - `ports`: Interfaces de entrada e saída
  - **enums**: Tipos como *TipoLancamento*
  - **security**
    - `config`, `jwt`, `model`, `service`: Componentes de autenticação/autorização
  - `Application.java`: Classe principal

---

## 🧩 Domínios Funcionais e Capacidades

| Domínio Funcional         | Capacidade de Negócio                  |
|---------------------------|----------------------------------------|
| Lançamentos Financeiros   | Registrar transações                   |
| Consolidação Financeira   | Calcular e disponibilizar saldo diário |
| Segurança e Acesso        | Autenticar e autorizar usuários        |
| Observabilidade           | Monitoraramento                        |

---

## 🔐 Segurança

- Autenticação via **JWT**
- Endpoints públicos: `/api/auth/**`, `/swagger-ui/**`, `/h2-console/**`
- Recomendação de uso de **HTTPS** em produção

---

## 🔄 Integração e Comunicação

- Comunicação via **REST**
- Suporte futuro a mensageria (**Kafka**, **RabbitMQ**, **SQS**) para desacoplamento

---

## 🔀 Resiliência com Resilience4j

A resiliência é garantida com **Resilience4j**, utilizando:

- **Circuit Breaker** com fallback para respostas padrão (ex: saldo zero)
- Configurações de timeout, tentativas e transições de estado via `application.properties`
- Suporte a até **50 requisições por segundo** com **menos de 5% de perda**
- Ajustes no **Tomcat** (threads, accept-count) para suportar carga

---

## ✅ Requisitos Funcionais

- CRUD de lançamentos financeiros
- Consolidação de saldo diário
- Exposição de APIs REST

---

## 📈 Requisitos Não Funcionais

- Operação independente entre serviços (ex: lançamentos funcionam sem consolidação)
- Suporte a alta carga com resiliência
- Segurança com **JWT**
- Monitoramento com **Spring Boot Actuator**, **CloudWatch** ou **Dynatrace**
- Documentação com **Swagger**

---

## 🧪 Testes Unitários

Testes com **JUnit 5** e **Mockito**, incluindo:

- Cálculo de saldo diário e fallback no `ControleLancamentoService`
- Testes de endpoints (`SaldoDiarioControllerTest`, `AuthController`, `LancamentoController`)
- Testes de integração para autenticação e lançamentos

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**, **Spring Boot**, **Maven**
- **Spring Security** + **JWT**
- **Spring Data JPA**, **H2**
- **Swagger** (Springdoc OpenAPI)
- **Resilience4j**
- **JUnit 5**, **Mockito**
- **Arquitetura Hexagonal**
- **Spring Boot Actuator**

---

## 🔍 Justificativa das Escolhas Técnicas

- **Arquitetura Hexagonal**: separação de responsabilidades e facilidade de testes
- **Spring Boot**: agilidade no desenvolvimento
- **Resilience4j**: robustez contra falhas
- **Mensageria (SQS)**: escalabilidade e desacoplamento
- **JUnit 5 + Mockito**: qualidade e segurança na refatoração

---

## 🚀 Evoluções Futuras

- Integração com agentes de IA utilizando Langchain para a geração dinâmica do saldo consolidado.
- Possibilidade de envio automático do saldo consolidado por email ou integração com outros serviços do cliente.
- Ampliação dos mecanismos de resiliência e monitoramento para suportar novas integrações.
- Otimizações na comunicação entre serviços e na arquitetura dos adaptadores.

## 🔄 Arquitetura de Transição (Legado → Novo)
Caso exista um sistema legado:

1. Criar adaptadores para o sistema legado
2. Migrar dados gradualmente
3. Utilizar mensageria para desacoplamento
4. Integrar API Gateway
5. Monitorar com CloudWatch/Dynatrace
6. Adicionar resiliência e observabilidade
7. Desativar o legado após estabilização

---

## 🔒 Critérios de Segurança para Integração

1. **Autenticação/Autorização**: JWT ou API Keys
2. **Criptografia**: TLS/SSL (A partir de certificado comprado (produção) ou gerado pelo keytool do Java (para testes locais))
3. **API Gateway**: controle de acesso
4. **Firewall/IPs confiáveis**
5. **Auditoria e Monitoramento**
6. **Rate Limiting**: Controle do número de requisições
7. **Validação de Dados**

---

## 💰 Estimativa de Custos
- ***Valores aproximados baseaando-se na calculadora AWS. O Valor vai variar conforme a migração***

| Item                     | Custo Estimado (mensal) |
|--------------------------|-------------------------|
| EC2 (1 instância t3)     | ~\$10                   |
| RDS PostgreSQL (db.t3)   | ~\$15                   |
| S3 (backups e logs)      | ~\$1                    |
| SQS (mensageria)         | ~\$2                    |
| **Total**                | **~\$28**               |

---

## ⏱️ Cronograma Estimado ***(Implantação inicial)***

| Etapa                                      | Dias |
|-------------------------------------------|------|
| Definição de Requisitos e Análise         | 1    |
| Configuração de Ambiente e Infraestrutura | 1    |
| API de Lançamentos                        | 2–3  |
| Consolidação e Resiliência                | 2    |
| Segurança e Autenticação                  | 1    |
| Integração e Testes                       | 1–2  |
| Ajustes Finais e Deploy                   | 1    |
| **Total Estimado**                        | **8–10 dias** |

