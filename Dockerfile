# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS builder
# Set the working directory
WORKDIR /app
# Copy the application code
COPY . .
# Construir el archivo .jar usando Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre
# Set the working directory
WORKDIR /app
# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar
# Expose the port the app will run on
EXPOSE 8080
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]