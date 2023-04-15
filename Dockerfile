FROM adoptopenjdk/openjdk11:alpine-jre

WORKDIR /opt/app
ARG JAR_FILE=target/spring-boot-web.jar

COPY ./target/test-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]