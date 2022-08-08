FROM maven:3.6.3 AS maven
LABEL MAINTAINER="htyesilyurt@gmail.com"
WORKDIR /opt/jumbo-store-service
COPY . /opt/jumbo-store-service
RUN mvn clean install 


FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=jumbo-store-service.jar
WORKDIR /opt/jumbo-store-service
COPY --from=maven /opt/jumbo-store-service/target/${JAR_FILE} /opt/jumbo-store-service/
ENTRYPOINT ["java","-jar","jumbo-store-service.jar"]