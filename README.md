# horas-complemantares-API
API do trabalho da disciplina APS.

## Pre-Requisites
- Java >= 17
- Postgres >= 17

Guias para instalação:
- [Java instalação](https://www.youtube.com/watch?v=QekeJBShCy4)
- [Postgres Instalação](https://www.youtube.com/watch?v=UbX-2Xud1JA)

## Instalação

1. Clone o repositório:
 
 ```bash
    git clone https://github.com/OliSammy/horas-complemantares-API
  ```

2. Crie o database e o schema no Postgres:
    - Abra o pgAdmin
  

       ![image](https://github.com/user-attachments/assets/6ade4d9d-6c20-4b62-9e7a-532375c989b7)

    - Crie o database             
   ![image](https://github.com/user-attachments/assets/aa8ffb5a-4201-4939-ad8c-73b33c7bec86)

3. Altere o arquivo `application.properties` :

 ```bash
spring.application.name=horas-complementares
spring.datasource.url=jdbc:postgresql://localhost:5432/[nome_do_DB]
spring.jpa.properties.hibernate.default_schema=[nome_no_DB]
spring.datasource.username=[seu_usuario]
spring.datasource.password=[sua_senha]
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.driver-class-name=org.postgresql.Driver
api.security.token.secret=meu_segredo_super_secreto
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
  ```
4. Execute o arquivo `C:\Users\samue\Downloads\horas-complemantares-API\src\main\java\com\uece\horas_complementares\HorasComplementaresApplication.java`
      
