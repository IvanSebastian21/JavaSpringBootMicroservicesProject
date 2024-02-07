# Guía para Levantar el Servidor

¡Bienvenido/a a nuestra aplicación! Esta guía te ayudará a levantar el servidor backend utilizando Spring Boot, un poderoso framework para el desarrollo de aplicaciones Java.

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalado lo siguiente en tu sistema:

- Java Development Kit (JDK) 8 o superior
- Maven

## Pasos para Levantar el Servidor

Sigue estos sencillos pasos para poner en marcha nuestro servidor:

1. **Clonar el Repositorio**
   Clona nuestro repositorio desde GitHub a tu máquina local utilizando el siguiente comando:
   ```bash
   git clone https://github.com/nombre-usuario/nombre-repositorio.git
   ```
   
2. **Navegar al Directorio**
   Ve al directorio de la aplicación clonada:
   ```bash
   cd nombre-repositorio
   ```
   
3. **Compilar el Proyecto**
   Utiliza Maven para compilar el proyecto:
   ```bash
   mvn clean install
   ```

4. **Ejecutar la Aplicación**
   Una vez compilado correctamente, puedes ejecutar la aplicación con el siguiente comando:
   ```bash
   mvn spring-boot:run
   ```

5. **¡Listo!**
   El servidor debería estar en funcionamiento ahora. Puedes acceder a él a través de `http://localhost:puerto`, donde `puerto` es el puerto configurado para el servidor.

## Breve Resumen del Servidor Backend Spring Boot

Nuestro servidor backend es un microservicio construido sobre el framework Spring Boot. Utiliza Java para proporcionar servicios RESTful para nuestra aplicación. Spring Boot simplifica el desarrollo de aplicaciones Java ofreciendo configuraciones por defecto sensatas y facilitando la creación de aplicaciones de forma rápida y sencilla. Este servidor backend sirve como el núcleo de nuestra aplicación, gestionando la lógica de negocio y la interacción con la base de datos. Con Spring Boot, podemos desarrollar y escalar nuestra aplicación de manera eficiente y robusta.
