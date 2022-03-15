FROM openjdk:11

LABEL maintainer="president20500@gmail.com"

EXPOSE 8080

ARG JAR_FILE=build/libs/cuppa-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} /cuppa-backend.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar","cuppa-backend.jar"]