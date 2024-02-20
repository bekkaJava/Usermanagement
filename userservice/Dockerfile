FROM openjdk:17-alpine
WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
