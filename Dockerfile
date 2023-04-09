# Base image
FROM openjdk:11-jdk-slim

RUN apt-get update -y && \
    apt-get install --no-install-recommends curl dnsutils net-tools -y  && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy JAR file and its dependencies
COPY target/parkinglot-0.0.2-SNAPSHOT.jar /app

# Set entrypoint
ENTRYPOINT ["java", "-jar", "/app/parkinglot-0.0.2-SNAPSHOT.jar"]