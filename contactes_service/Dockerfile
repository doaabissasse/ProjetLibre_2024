# Use the official Maven image to build the project
FROM maven:3.8.8-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files to the container
COPY . .

# Build the application
RUN mvn clean install -DskipTests

# Run the application
CMD ["mvn", "spring-boot:run"]

# Expose the application's port
EXPOSE 8084
