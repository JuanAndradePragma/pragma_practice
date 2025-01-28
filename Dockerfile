FROM openjdk:17-jdk-slim
EXPOSE 8080
ADD target/pragma_practice.jar pragma_practice.jar
ENTRYPOINT ["java", "-jar", "/pragma_practice.jar"]