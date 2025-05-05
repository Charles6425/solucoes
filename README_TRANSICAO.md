## 🧩 Componentes e fluxo
- Legacy_System: representa o sistema atual em produção.

- L1: banco de dados legado.
- L2: aplicação monolítica ou acoplada.
- Transition_Components:

- T1: serviço de migração de dados (pode ser batch ou streaming).
- T2: adaptador de integração que permite que o novo sistema consuma dados ou funcionalidades do legado.

### New_System:

- N1: novo banco de dados (isolado).
- N2: nova aplicação com arquitetura hexagonal.
- N3: API Gateway para unificar o acesso e rotear chamadas.
- N4: fila SQS para comunicação assíncrona entre serviços.