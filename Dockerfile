# We want the newest LTS version.
FROM eclipse-temurin:21-jre

WORKDIR /app

# The JAR file is the only thing we need. It is created by "mvn package".
COPY target/*.jar app.jar

# Create non-root user for running the process.
RUN useradd --create-home --shell /usr/sbin/nologin appuser \
    && chown appuser:appuser /app/app.jar
USER appuser

# Documents that the application listens on port 8080 inside the container.
EXPOSE 8080

# Start the JAR file after container boot-up.
CMD ["java", "-jar", "/app/app.jar"]
