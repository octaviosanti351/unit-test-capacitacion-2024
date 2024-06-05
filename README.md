Pasos para levantar el proyecto:

- clonarlo en una carpeta
- sincronizar las librerias y dependencias (ir al archivo build.gradle y darle a sync)
- generar la base de datos (explicado mas abajo)
- correr el proyecto y entrar a localhost:8080/swagger-ui.html 

Para generar la base de datos con dummy data:

```
docker run --name postgres-db -e POSTGRES_PASSWORD=docker -p 5432:5432 -d postgres 
```
Copiar dummy-data.sql en la carpeta resources y correrlo en un script en postgres

Versión de java: 17 en adelante
