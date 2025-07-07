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
```scss
          [🔗 Entrada - REST Controller]
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ CategoriaController (Adapter In)   │
        └────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ CategoriaService (Port de Entrada) │◄────────────┐
        └────────────────────────────────────┘             │
                        │                                  │
                        ▼                                  │
        ┌────────────────────────────────────┐             │
        │ CategoriaServiceImpl (Application) │             │
        └────────────────────────────────────┘             │
                        │                                  │
                        ▼                                  │
        ┌────────────────────────────────────┐             │
        │ CategoriaRepository (Port de Saída)│─────────────┘
        └────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────────┐
        │ CategoriaJpaRepository (Adapter Out)   │
        └────────────────────────────────────────┘
                        │
                        ▼
        ┌────────────────────────────────────┐
        │ CategoriaEntity (JPA)              │
        └────────────────────────────────────┘

                    ↑           ↑
                    │           │
     CategoriaRequest       CategoriaResponse
          (DTO In)               (DTO Out)

                    ↑           ↑
                    └──── Mapeamento ─────┘

                        ▼
        ┌────────────────────────────────────┐
        │ Categoria (Domain Model)           │
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