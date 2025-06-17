FROM docker.io/openjdk:17-jdk-slim

COPY ../target/onlineshop-0.0.1-SNAPSHOT.jar /app/onlineshop.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-Xmx384m", "-XX:MaxMetaspaceSize=256m", "-jar", "onlineshop.jar"]