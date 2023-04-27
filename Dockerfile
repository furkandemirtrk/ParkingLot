# Base image
FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.11_9-slim


# Set working directory
WORKDIR /app

# Copy JAR file and its dependencies
COPY target/parkinglot-0.0.2-SNAPSHOT.jar /app

# Set entrypoint
ENTRYPOINT ["java", "-jar", "/app/parkinglot-0.0.2-SNAPSHOT.jar"]
