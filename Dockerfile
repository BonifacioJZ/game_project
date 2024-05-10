#FROM maven:3-amazoncorretto-17-debian-bullseye as build

#LABEL authors="bonifacio"
#WORKDIR app/
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests
FROM openjdk:jdk-bullseye
EXPOSE 8080
COPY target/game_project-0.0.1-SNAPSHOT.jar game_project.jar
ENTRYPOINT ["java", "-jar","game_project.jar"]