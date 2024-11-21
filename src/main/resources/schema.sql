 -- Eliminar las tablas si existen
 DROP TABLE IF EXISTS phone CASCADE;
 DROP TABLE IF EXISTS person CASCADE;

 -- Crear la tabla person
 CREATE TABLE person (
     id UUID NOT NULL PRIMARY KEY,
     created DATE,
     is_active BOOLEAN NOT NULL,
     last_login DATE,
     updated DATE,
     email VARCHAR(255),
     name VARCHAR(255),
     password VARCHAR(255),
     token VARCHAR(255)
 );

 -- Crear la tabla phone
 CREATE TABLE phone (
     id UUID NOT NULL PRIMARY KEY,
     person_id UUID,
     city_code VARCHAR(255),
     contry_code VARCHAR(255),
     number VARCHAR(255),
     CONSTRAINT FK_phone_person FOREIGN KEY (person_id) REFERENCES person(id)
 );