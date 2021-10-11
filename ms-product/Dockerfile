FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/ms-product-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} ms-product.jar
ENTRYPOINT ["java","-jar","/ms-product.jar"]