# Horas Complementares API

> API para gerenciamento de horas complementares de alunos da Universidade Estadual do Ceará (UECE)

Uma aplicação backend robusta desenvolvida em Spring Boot 3 para gerenciar eventos, inscrições, presenças e geração de QR Codes para controle de horas complementares estudantis.

---

## 📋 Índice

- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Arquitetura e Organização](#arquitetura-e-organização)
- [Funcionalidades](#funcionalidades)
- [Medidas de Segurança](#medidas-de-segurança)
- [Bibliotecas Utilizadas](#bibliotecas-utilizadas)

---

## 🔧 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java 17 ou superior** ([Guia de instalação](https://www.youtube.com/watch?v=QekeJBShCy4))
- **PostgreSQL 17 ou superior** ([Guia de instalação](https://www.youtube.com/watch?v=UbX-2Xud1JA))
- **Git** para clonar o repositório
- **Maven 3.6+** (incluído no projeto via `mvnw`)

---

##Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/OliSammy/horas-complemantares-API
cd horas-complemantares-API
```

### 2. Configure o banco de dados PostgreSQL

Abra o **pgAdmin** e crie um novo database:

- Clique em `Databases` → `Create` → `Database`
- Nomeie-o (ex: `horas_complementares`)
- Anote as credenciais de acesso (usuário e senha)

### 3. Configure o arquivo `application.properties`

Edite `src/main/resources/application.properties` com suas credenciais:

```properties
spring.application.name=horas-complementares
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_database
spring.jpa.properties.hibernate.default_schema=seu_database
spring.datasource.username=seu_usuario_postgres
spring.datasource.password=sua_senha_postgres
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.driver-class-name=org.postgresql.Driver

# Segurança - Token JWT (substitua com uma chave segura)
api.security.token.secret=sua_chave_secreta_muito_segura

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
```

### 4. Execute a aplicação

**Opção A - Usando Maven (Linux/Mac):**
```bash
./mvnw spring-boot:run
```

**Opção B - Usando Maven (Windows):**
```bash
mvnw.cmd spring-boot:run
```

**Opção C - Executar via IDE:**
- Abra o projeto em sua IDE (IntelliJ, Eclipse, VS Code)
- Execute a classe `HorasComplementaresApplication.java`

A API estará disponível em: `http://localhost:8080`

---

## 🏗️ Arquitetura e Organização

O projeto segue o padrão **MVC (Model-View-Controller)** com separação clara de responsabilidades:

### Estrutura de Diretórios

```
src/main/java/com/uece/horas_complementares/
├── controller/               # Controladores REST
│   ├── AlunoController.java
│   ├── AuthController.java
│   ├── EventosController.java
│   ├── HorasComplementaresController.java
│   ├── ProfessorController.java
│   └── RegistrationController.java
│
├── model/                   # Modelos de dados
│   ├── user/               # Entidades de usuários
│   │   ├── User.java       # Classe base para usuários
│   │   ├── Aluno.java
│   │   ├── Professor.java
│   │   └── Coordenador.java
│   ├── Evento.java         # Eventos de horas complementares
│   ├── Inscricao.java      # Inscrições de alunos
│   ├── Presenca.java       # Controle de presença
│   ├── HoraComplementar.java # Registro de horas
│   ├── Curso.java
│   ├── SubCategoria.java
│   │
│   ├── DTO/                # Data Transfer Objects
│   │   ├── evento/         # DTOs de eventos
│   │   └── user/           # DTOs de autenticação/usuários
│   │
│   ├── repository/         # Camada de acesso a dados (JPA)
│   │   ├── UserRepository.java
│   │   ├── EventoRepository.java
│   │   ├── InscricaoRepository.java
│   │   ├── PresencaRepository.java
│   │   └── ...QueryClasses (queries customizadas)
│   │
│   ├── spec/              # Especificações para queries dinâmicas
│   │   ├── AlunoByIdSpec.java
│   │   ├── EventoByProfessor.java
│   │   └── ...
│   │
│   └── exception/         # Exceções customizadas
│       ├── auth/
│       ├── token/
│       └── user/
│
├── service/               # Lógica de negócios
│   ├── auth/             # Serviços de autenticação
│   ├── evento/           # Serviços de eventos
│   ├── horasComplementares/
│   ├── inscricaoService/
│   ├── presenca/
│   ├── qrCode/           # Geração de QR Codes
│   ├── register/
│   └── users/
│
├── security/             # Configurações de segurança
│   ├── SecurityConfigurations.java
│   ├── SecurityFilter.java
│   └── TokenService.java
│
├── util/                 # Utilitários
│   ├── ImageToBase64Converter.java
│   └── RestExceptionHandler.java
│
├── CorsConfig.java       # Configuração de CORS
└── HorasComplementaresApplication.java

src/main/resources/
└── application.properties
```

### Padrões Arquiteturais Utilizados

| Padrão | Descrição | Localização |
|--------|-----------|-------------|
| **MVC** | Separação entre Controller, Service e Repository | controller/, service/, repository/ |
| **DTO** | Transferência de dados entre camadas | model/DTO/ |
| **Repository Pattern** | Abstração da camada de acesso a dados | model/repository/ |
| **Service Layer** | Lógica de negócios isolada | service/ |
| **Specification Pattern** | Queries dinâmicas e reutilizáveis | model/spec/ |
| **JWT Authentication** | Autenticação stateless com tokens | security/TokenService.java |
| **Exception Handling** | Tratamento centralizado de erros | util/RestExceptionHandler.java |

---

## 🎯 Funcionalidades

###  Gestão de Alunos
- Registro e autenticação de alunos
- Perfil e visualização de horas complementares
- Inscrição em eventos disponíveis
- Acompanhamento de presenças

###  Gestão de Eventos
- Criação de eventos por professores
- Categorização e descrição detalhada
- Inserção de imagens de eventos
- Gerenciamento de inscrições de alunos
- Visualização de alunos inscritos

###  Controle de Presença
- Geração de **QR Codes únicos** por evento
- Leitura de QR Codes para marcar presença
- Registro automático de horas complementares
- Validação de presença por professor

###  Validação de Horas Complementares
- Cálculo automático de horas cumpridas
- Rastreamento de progresso do aluno
- Integração com categorias e sub-categorias
- Relatórios de horas por categoria

### Controle de Acesso por Papel
- **Aluno**: Visualizar eventos, inscrever-se, marcar presença
- **Professor**: Criar eventos, validar presenças via QR Code
- **Coordenador**: Gerenciamento geral do sistema

---

## Medidas de Segurança

### 1. **Autenticação e Autorização**
- ✅ **Autenticação JWT**: Tokens seguros baseados em JWT (Auth0 java-jwt 4.4.0)
- ✅ **Stateless Sessions**: Sessões stateless para escalabilidade
- ✅ **Criptografia de Senhas**: BCrypt com force 12 para senhas
- ✅ **Controle de Acesso por Rol**: Autorização baseada em papéis (Role-Based Access Control - RBAC)

### 2. **Proteção de Requisições HTTP**
- ✅ **CSRF Disabled**: Configurado para APIs stateless (sem CSRF)
- ✅ **CORS**: Configuração customizada para origens autorizadas
- ✅ **HTTPS Ready**: Compatível com SSL/TLS

### 3. **Segurança em Endpoints**
- ✅ **Validação de Input**: Jakarta Bean Validation em todos os DTOs
- ✅ **Autorização por Endpoint**: Cada rota protegida conforme seu tipo de usuário
- ✅ **QR Code Único**: Tokens únicos por aluno/evento para presença

### 4. **Proteção de Dados**
- ✅ **Senhas Criptografadas**: Armazenadas com BCrypt
- ✅ **Filtro de Segurança**: SecurityFilter para validação de tokens
- ✅ **Tratamento de Exceções**: RestExceptionHandler para respostas seguras

### 5. **Endpoints Públicos (sem autenticação)**
```
POST   /auth/login                 - Autenticação de usuários
POST   /registro/criar             - Criação de conta
GET    /v3/api-docs                - Documentação Swagger
GET    /swagger-ui/**              - Interface Swagger
GET    /uploads/**                 - Acesso a uploads de imagens
```

---

## 📚 Bibliotecas Utilizadas

### Core Framework
| Biblioteca | Versão | Função |
|-----------|--------|--------|
| **Spring Boot** | 3.4.2 | Framework principal |
| **Spring Security** | 6.x | Autenticação e autorização |
| **Spring Data JPA** | 3.x | Persistência e ORM |
| **Spring Web** | 3.x | Controladores REST |

### Segurança
| Biblioteca | Versão | Função |
|-----------|--------|--------|
| **java-jwt (Auth0)** | 4.4.0 | Geração e validação de JWT |
| **BCrypt** | Embutido | Criptografia de senhas |

### Geração de QR Codes
| Biblioteca | Versão | Função |
|-----------|--------|--------|
| **ZXing (core)** | 3.5.2 | Engine de QR Code |
| **ZXing (javase)** | 3.5.2 | Suporte para aplicações Java SE |

### Banco de Dados
| Biblioteca | Versão | Função |
|-----------|--------|--------|
| **PostgreSQL Driver** | 42.3.1 | Conexão com PostgreSQL |
| **Hibernate** | Embutido | ORM (Object-Relational Mapping) |

### Utilidades
| Biblioteca | Versão | Função |
|-----------|--------|--------|
| **Lombok** | 1.18.32 | Redução de boilerplate (getters, setters, etc) |
| **Jakarta Persistence** | 2.2 | Anotações JPA |
| **Spring Validation** | 3.x | Validação de dados |

### Documentação & API
| Biblioteca | Versão | Função |
|-----------|--------|--------|
| **SpringFox** | 3.0.0 | Geração automática de Swagger/OpenAPI |

### Desenvolvimento
| Biblioteca | Versão | Função |
|-----------|--------|--------|
| **Spring DevTools** | 3.x | Hot reload em desenvolvimento |
| **Spring Test** | 3.x | Testes unitários e integração |
| **Spring Security Test** | 3.x | Testes de segurança |

---

## 📖 Documentação da API

A documentação interativa da API está disponível via **Swagger UI**:

```
http://localhost:8080/swagger-ui.html
```

Ou no formato OpenAPI:
```
http://localhost:8080/v3/api-docs
```

---

## 📝 Notas Importantes

- **Segredo JWT**: Altere `api.security.token.secret` em `application.properties` com uma chave forte
- **Modo DDL**: `spring.jpa.hibernate.ddl-auto=update` cria/atualiza tabelas automaticamente
- **Diretório de Uploads**: Imagens de eventos são salvas em `src/main/resources/uploads/`
- **Logs SQL**: Desabilitar `spring.jpa.show-sql=true` em produção

---

      
