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

## 🛠️ Comandos úteis para desenvolvimento

### 🧹 1. Limpar e empacotar a aplicação (sem rodar testes)

```bash
mvn clean package -DskipTests
```
Este comando:

- 🧽 Apaga arquivos de build antigos (pasta target)

- 🛠️ Compila o projeto

- 📦 Gera o .jar final

- 🚫 Não executa os testes

### ▶️ 2. Executar a aplicação diretamente com Maven
```bash
mvn spring-boot:run
```

Carrega a aplicação usando o application.properties padrão

Tenta conectar ao banco configurado no perfil ativo  

⚠️ Atenção:
Esse comando pode falhar caso o banco PostgreSQL não esteja em execução ou as credenciais estejam incorretas.  

### 📦 3. Executar o .jar gerado
```bash
java -jar target/feira-rural-api-0.0.1-SNAPSHOT.jar
```
Necessário executar mvn clean package antes ou apagar o dossier target em caso de erros.

### 🌱 4. Rodar com um perfil específico (ex: produção)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

Ou:

```bash
java -jar target/feira-rural-api-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### ✅ 5. Executar testes unitários
```bash
mvn test
```

### 🔄 6. Atualizar dependências locais (cache)
```bash
mvn dependency:purge-local-repository
mvn clean install
```

# 📁 Estrutura esperada após o build
Após executar mvn clean package, a estrutura do projeto será semelhante a:

```pgsql
feira-rural-api/
├── target/
│   ├── feira-rural-api-0.0.1-SNAPSHOT.jar
│   └── classes/
│   └──...
├── src/
├── pom.xml
└── README.md
```

# 📁 Implementando uma arquitetura hexagonal (Ports and Adapters) orientada a funcionalidades  
```pgsql
src/
└── main/
    └── java/
        └── com/
            └── feirarural/
                └── api/
                    └── categoria/
                        ├── domain/
                        │   ├── model/
                        │   │   └── Categoria.java
                        │   └── port/
                        │       ├── CategoriaRepository.java     // porta de saída
                        │       └── CategoriaService.java        // porta de entrada
                        ├── application/
                        │   └── service/
                        │       └── CategoriaServiceImpl.java    // implementação do caso de uso
                        ├── adapter/
                        │   ├── in/
                        │   │   └── rest/
                        │   │       └── CategoriaController.java
                        │   └── out/
                        │       └── persistence/
                        │           └── CategoriaJpaRepository.java
                        └── dto/
                            ├── CategoriaRequest.java
                            └── CategoriaResponse.java
```

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