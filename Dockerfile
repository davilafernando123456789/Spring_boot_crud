# Utiliza una imagen base de Maven para construir la aplicación
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml y los archivos de configuración
COPY pom.xml .

# Copia el resto de los archivos del proyecto
COPY src ./src

# Compila la aplicación
RUN mvn clean package

# Utiliza una imagen base de Java para ejecutar la aplicación
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR desde la fase de construcción
COPY --from=build /app/target/spring-boot-ventas-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto que la aplicación va a utilizar
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
