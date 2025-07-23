# 📦 feira-rural-api

API backend da aplicação **Feira Rural**, desenvolvida com **Spring Boot** e **PostgreSQL**.

---

## 🎯 Contribuição aos Objetivos da ONU (ODS 2)

A aplicação **Feira Rural** está alinhada com os Objetivos de Desenvolvimento Sustentável da ONU, em especial o **ODS 2 – Fome Zero e Agricultura Sustentável**, ao:

- 🌾 Apoiar pequenos produtores rurais, ampliando sua renda e visibilidade digital
- 🛒 Promover mercados locais, com cadeias curtas e sustentáveis de comercialização
- 🍅 Facilitar o acesso da população a alimentos frescos, nutritivos e acessíveis
- ✅ Reduzir perdas e desperdícios com vendas diretas sob demanda
- 📚 Incentivar práticas alimentares saudáveis por meio de informações sobre sazonalidade e aproveitamento integral
- 🤝 Fortalecer parcerias entre produtores, consumidores e iniciativas públicas ou comunitárias

Esse alinhamento torna o projeto uma ferramenta concreta de transformação social, com potencial para integrar políticas públicas, programas de financiamento social e estratégias de desenvolvimento sustentável em comunidades rurais e urbanas.

---

## 📋 Pré-requisitos

Antes de executar os comandos abaixo, certifique-se de ter:

- ✅ Java 17 ou superior instalado
- ✅ Maven 3.8 ou superior instalado
- ✅ PostgreSQL em execução com banco `feira-rural-db`
- ✅ Variáveis de ambiente ou `application.properties` configurados corretamente

---

Abaixo está um diagrama simples mostrando como os principais elementos da funcionalidade categoria se comunicam dentro da arquitetura hexagonal (Ports and Adapters) no seu projeto feira-rural-api. Usei uma estrutura visual para facilitar a leitura:
```
          [🔗 Entrada - REST Controller]
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ EntidadeController                 │ ◄────────────── Adapter In (REST)
        └────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ EntidadeUseCase                    │ ◄────────────── Porta de Entrada (interface)
        └────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ EntidadeServiceImpl                │ ◄────────────── Application / Service
        └────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ EntidadeRepository                 │ ◄────────────── Porta de Saída (interface)
        └────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ EntidadeRepositoryAdapter          │ ◄────────────── Adapter Out (infraestrutura)
        └────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ EntidadeRepositoryJpa              │ ◄────────────── JPA (Spring Data)
        └────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ EntidadeEntity (JPA)               │ ◄────────────── Representação persistente
        └────────────────────────────────────┘


        ◄────────────── Mapeamento ───────────────►
   EntidadeRequest (DTO In)        EntidadeResponse (DTO Out)
                │                           │
                ▼                           ▼
        ┌────────────────────────────────────┐
        │ EntidadeMapper                     │ ◄────────────── Traduz entre DTOs e Domínio
        └────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ Entidade (Domínio)                 │ ◄────────────── Núcleo do negócio
        └────────────────────────────────────┘

```

# 🧩 Resumo das responsabilidades:
Controller: recebe requisições HTTP.  

DTOs: transportam dados entre cliente e backend.  

Service (Interface + Impl): contém a lógica de negócio da aplicação.  

Portas (Ports): interfaces que abstraem dependências (entrada e saída).  

Adapters (In/Out): implementam as portas, lidando com REST ou persistência.  

Domain Model: representa a entidade central da lógica de negócio.  

Entity JPA: representa a entidade para persistência no banco de dados.  

```
/src/main/java/com/dominio/projeto/entidade/
│
├── adapter/                                      # Adaptadores: interfaces externas do sistema
│   ├── in/                                       # Entrada do sistema (ex: HTTP, CLI, mensageria)
│   │   └── rest/                                 # Adaptador REST (entrada por API HTTP)
│   │       └── EntidadeController.java           # Recebe requisições HTTP → chama casos de uso
│   └── out/                                      # Saída do sistema (banco, APIs externas, etc.)
│       └── persistence/                          # Adaptador de persistência (infraestrutura)
│           ├── EntidadeRepositoryJpa.java        # Interface JPA para manipular a entidade
│           └── EntidadeRepositoryAdapter.java    # Implementa a porta de saída do domínio
│
├── application/                                  # Camada de aplicação (coordena os casos de uso)
│   ├── service/                                  # Implementações dos casos de uso definidos no domínio
│   │   └── EntidadeServiceImpl.java              # Implementa os casos de uso da entidade
│   └── mapper/                                   # Conversores entre DTOs ↔ Domínio
│       └── EntidadeMapper.java                   # Tradução de request/response para o modelo de domínio
│
├── domain/                                       # Núcleo de negócio (regra de negócio pura)
│   ├── model/                                    # Entidades de domínio e suas regras
│   │   └── Entidade.java                         # Entidade central com identidade própria
│   └── port/                                     # Portas (interfaces) de entrada e saída do domínio
│       ├── in/                                   # Porta de entrada (define o que o sistema faz)
│       │   └── EntidadeUseCase.java              # Interface dos casos de uso do domínio
│       └── out/                                  # Porta de saída (define o que o sistema precisa)
│           └── EntidadeRepository.java           # Interface de persistência esperada pelo domínio
│
├── dto/                                          # Objetos de transporte (camada REST)
│   ├── EntidadeRequest.java                      # Dados recebidos via HTTP (entrada)
│   └── EntidadeResponse.java                     # Dados devolvidos via HTTP (saída)

```