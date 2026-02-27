# Etapa 1 - Build completo do projeto multimódulo
FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /build

# Copia o super-pom primeiro
COPY pom.xml .

# Copia os POMs dos módulos (para o Maven resolver dependências offline)
COPY framework-api-restfull-crud/pom.xml framework-api-restfull-crud/
COPY use-api-framework/pom.xml use-api-framework/

# Instala o framework no repositório local do build para que a API possa encontrá-lo
RUN mvn dependency:go-offline -B

# Copia os fontes dos módulos
COPY framework-api-restfull-crud/src framework-api-restfull-crud/src
COPY use-api-framework/src use-api-framework/src

# Build completo: o Maven já resolve a ordem (framework antes da api)
RUN mvn clean install -DskipTests



# Etapa 2 - Runtime
FROM eclipse-temurin:11-jre-alpine
WORKDIR /app

COPY --from=build /build/use-api-framework/target/api-rest.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]