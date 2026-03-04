FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /build

COPY pom.xml .

# Copia os POMs dos módulos (para o Maven resolver dependências offline)
COPY framework-api-restfull-crud/pom.xml framework-api-restfull-crud/
COPY use-api-framework/pom.xml use-api-framework/

# Instala o framework no repositório local do build para que a API possa encontrá-lo
RUN mvn dependency:go-offline -B

COPY framework-api-restfull-crud/src framework-api-restfull-crud/src
COPY use-api-framework/src use-api-framework/src

RUN mvn clean install -DskipTests


#---------------

    
FROM eclipse-temurin:11-jre-alpine
WORKDIR /app

COPY --from=build /build/use-api-framework/target/api-rest.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]