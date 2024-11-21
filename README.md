# BCI API

Este es un proyecto API RESTful de usuarios utilizando **Spring Boot 3.3.5**, **Java 17**, **Lombok**, y **JWT** para generar el token. La API proporciona operaciones para creación de usuarios, validación de parametros de entrada y una base de datos H2 en memoria.

## Requisitos

- **Java 17**
- **Maven** 3.x
- **Spring Boot 3.3.5**
- **Lombok**

## Descripción

Este proyecto proporciona una API que permite realizar la creación de usuarios y generar un token mediante **JWT**. El backend está configurado con una base de datos en memoria H2 para almacenamiento temporal durante el desarrollo. Utiliza **Lombok** para reducir el código boilerplate.

## Dependencias

- **Spring Boot Starter Web**: para construir una API RESTful.
- **Spring Boot Starter Data JPA**: para trabajar con bases de datos utilizando JPA.
- **Spring Boot Starter Security**: para manejar la seguridad y autenticación de la API.
- **Spring Boot Starter Validation**: para validar las solicitudes de los usuarios.
- **SpringDoc OpenAPI**: para generar documentación Swagger de la API.
- **H2 Database**: base de datos en memoria para pruebas.
- **Lombok**: para reducir el código.
- **JJWT**: para la generación de tokens JWT.

## Instalación

### Clonar el repositorio

```bash
git clone https://github.com/tu_usuario/tu_repositorio.git
cd carpeta_repositorio
```

### Asegúrate de tener Java 17 instalado. Puedes verificarlo con:
```bash
java -version
```

### Construye el proyecto usando Maven:
```bash
mvn clean install
```

### Ejecute el proyecto en tu máquina local, usando comandos Maven:
```bash
mvn spring-boot:run
```

La API estará disponible en http://localhost:8081.

## Swagger (OpenAPI)
La documentación de la API se genera automáticamente con Swagger. Puedes acceder a ella en:
```bash
http://localhost:8081/swagger-ui.html
```

Ejemplos para consumo:
```bash
{
  "name": "Juan Rodriguez",
  "email": "juan@dominio.cl",
  "password": "Secure#9",
  "phones": [
    {
      "number": "1234567",
      "cityCode": "1",
      "contryCode": "57"
    }
  ]
}
```

```bash
curl --location 'http://localhost:8081/api/v1/bci/persons' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Juan Rodríguez",
    "email": "juanrodriguet@empresa.cl",
    "password": "Secure@123",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "contryCode": "57"
        },
        {
            "number": "9876543",
            "cityCode": "2",
            "contryCode": "56"
        }
    ]
}
'
```