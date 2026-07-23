# ===== Build Stage =====
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies (no "go-offline")
COPY pom.xml .
RUN mvn clean verify -DskipTests || true

# Copy source code and build the jar
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Run Stage =====
FROM gcr.io/distroless/java17-debian12
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
