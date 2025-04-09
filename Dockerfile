# Use a base Java image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file into the container
COPY target1/*.jar app.jar

# Expose the port (Render sets $PORT env var)
EXPOSE 8080

# Start the app
CMD ["java", "-jar", "app.jar"]
