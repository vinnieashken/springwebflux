FROM openjdk:23-jdk-slim

# Add a label (optional)
LABEL maintainer="ASHKEN"

# Set the working directory
WORKDIR /app

# Copy the built jar to the container
COPY target/*.jar app.jar

# Optionally, copy an externalized properties file (if needed)
# COPY config/application.properties /app/config/

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]