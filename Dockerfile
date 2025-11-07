# ===== Build Stage =====
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Run Stage =====
FROM gcr.io/distroless/java17-debian12
WORKDIR /app

# Copy built JAR from previous stage
COPY --from=build /app/target/*.jar app.jar

ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]
