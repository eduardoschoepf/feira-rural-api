# 📦 feira-rural-api

API backend da aplicação **Feira Rural**, desenvolvida com **Spring Boot** e **PostgreSQL**.

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
├── src/
├── pom.xml
└── README.md
```