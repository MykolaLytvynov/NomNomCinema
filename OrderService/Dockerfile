FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory in the build stage
WORKDIR /app

# Copy only the parent POM (for dependency caching)
COPY pom.xml .

# Copy the POM of each module to download dependencies before copying the code
COPY commons/pom.xml ./commons/
COPY OrderService/pom.xml ./OrderService/
COPY BuffetService/pom.xml ./BuffetService/

# Download dependencies (without building) to leverage caching
RUN mvn dependency:go-offline -pl commons,OrderService -am

# Copy the source code
COPY commons/ ./commons/
COPY OrderService/ ./OrderService/

# Build only the required modules
RUN mvn clean package -pl commons,OrderService -am -DskipTests

# Create the final image
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the built JAR from the previous container
COPY --from=build /app/OrderService/target/OrderService-0.0.1-SNAPSHOT.jar OrderService-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "OrderService-0.0.1-SNAPSHOT.jar"]
