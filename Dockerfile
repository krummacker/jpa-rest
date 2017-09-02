FROM openjdk:latest

COPY target/jpa-rest-0.1.0.jar /usr/src/jpa-rest-0.1.0.jar

CMD java -jar /usr/src/jpa-rest-0.1.0.jar

