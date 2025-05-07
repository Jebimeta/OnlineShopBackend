FROM openjdk:17

WORKDIR /app

COPY target/ritarouge-0.0.1-SNAPSHOT.jar /app/ritarouge.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/ritarouge.jar"]
