FROM openjdk:17-jdk-alpine

COPY ./target/onlineshop-0.0.1-SNAPSHOT.jar app.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app.jar" ]