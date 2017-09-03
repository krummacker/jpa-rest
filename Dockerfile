# We want Java 8.
FROM openjdk:latest

# The JAR file is the only thing we need. It is created by "mvn package".
COPY target/jpa-rest-0.1.0.jar /usr/src/jpa-rest-0.1.0.jar

# Start the JAR file after container boot-up.
CMD java -jar /usr/src/jpa-rest-0.1.0.jar

# Signals that we serve data on port 8080. Needed by IDEA to be able to publish that port.
EXPOSE 8080
