FROM alpine:3.15
RUN apk add openjdk11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} test.jar
ENTRYPOINT ["java", "-jar", "/test.jar"]
