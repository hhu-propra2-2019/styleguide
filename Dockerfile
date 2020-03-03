FROM openjdk:11-jre-slim

COPY build/libs/*.jar app/app.jar

CMD java -jar app/app.jar