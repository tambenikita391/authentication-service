# Use the latest Ubuntu image
FROM ubuntu:latest

# Update and install OpenJDK
RUN apt update && apt install -y openjdk-17-jdk

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file to /app
COPY authentication-service-HW-0.0.1-SNAPSHOT.jar /app/authentication-service.jar

# Define the command to run the JAR file
CMD ["java", "-jar", "authentication-service.jar"]
