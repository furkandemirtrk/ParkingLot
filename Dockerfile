# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy JAR file and its dependencies
COPY target/parkinglot-0.0.2-SNAPSHOT.jar /app

# Set entrypoint
ENTRYPOINT ["java", "-jar", "/app/parkinglot-0.0.2-SNAPSHOT.jar"]