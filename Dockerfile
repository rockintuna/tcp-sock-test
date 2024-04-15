FROM openjdk:21-jdk-slim
COPY ./build/libs/tcp-sock-test-1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]