FROM maven:3.6.3 AS build

LABEL MAINTAINER="ric.martikan@gmail.com"

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package 

# For Java 11, 
FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=*.jar

WORKDIR /opt/app

COPY --from=build /usr/src/app/target/${JAR_FILE} /opt/app/api.jar

ENTRYPOINT ["java","-jar","api.jar"]