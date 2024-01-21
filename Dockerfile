
# Use a base image with Java 8
FROM openjdk:8-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/LoopDFSCreditAccount-0.0.1-SNAPSHOT.jar /app/LoopDFSCreditAccount-0.0.1-SNAPSHOT.jar

COPY mvnw pom.xml log4j2-spring.xml ./

# Expose the port that your application will run on
EXPOSE 8060

# Specify the command to run on container start
CMD ["java", "-jar", "LoopDFSCreditAccount-0.0.1-SNAPSHOT.jar"]
