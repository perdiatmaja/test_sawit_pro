FROM eclipse-temurin:17-jdk-alpine

WORKDIR /usr/app
ARG JAR_FILE=./target/spring-boot-web.jar

COPY JAR_FILE app.jar
ENTRYPOINT ["java","-jar","app.jar"]