## 🧾 Explicação detalhada dos componentes e conexões

## 🟦 VPC (Virtual Private Cloud)
- É a rede principal que agrupa todos os recursos.
Fornece isolamento e controle de tráfego entre sub-redes e serviços.
## 🌐 Internet Gateway
- Permite que recursos dentro da VPC (como EC2) acessem a internet.
Está conectado à VPC e roteado via a Route Table.
## 🛣️ Route Table
- Define regras de roteamento para as sub-redes públicas.
Está associada às sub-redes públicas para permitir tráfego externo via o Internet Gateway.
## 🌐 Sub-redes
- PublicSubnet1 e PublicSubnet2: conectadas à Internet via Route Table.
- Hospedam as instâncias EC2 (serviços da aplicação).
- PrivateSubnet1 e PrivateSubnet2: isoladas da internet.
Usadas para o banco de dados RDS, garantindo segurança.
## 🛡️ Security Group
Atua como firewall virtual.
Controla o tráfego de entrada e saída para EC2 e RDS.
Está associado a todos os recursos computacionais.
## 🖥️ EC2Instance1 e EC2Instance2
Instâncias Linux para rodar os serviços Spring Boot:
Uma para lançamentos financeiros
Outra para consolidação
Estão em sub-redes públicas para permitir acesso externo (ex: via SSH ou API).
## 🗄️ RDSInstance
Banco de dados PostgreSQL.
Está em sub-redes privadas para maior segurança.
Conectado ao Security Group para controle de acesso.
## 📬 SQSQueue
Fila de mensagens para comunicação assíncrona entre os serviços.
Permite desacoplamento entre os microsserviços (ex: EC2Instance1 envia mensagens que EC2Instance2 consome).