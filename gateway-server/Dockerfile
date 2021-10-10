FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8090
ARG JAR_FILE=target/gateway-server-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} gateway-server.jar
ENTRYPOINT ["java","-jar","/gateway-server.jar"]