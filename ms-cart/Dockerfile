FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/ms-cart-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} ms-cart.jar
ENTRYPOINT ["java","-jar","/ms-cart.jar"]