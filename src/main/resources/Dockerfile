FROM alpine:latest
RUN apk --update add openjdk8-jre
COPY target/witcher4.jar witcher4.jar
ENTRYPOINT ["java", "-jar", "witcher4.jar"]