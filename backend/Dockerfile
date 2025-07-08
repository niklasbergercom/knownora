# Dockerfile for Spring Boot backend
FROM eclipse-temurin:21-jdk-alpine

# Set work directory
WORKDIR /app

# Copy jar file
COPY target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]