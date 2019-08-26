FROM alpine:latest
RUN apk --update add openjdk8-jre
COPY witcher4.jar witcher4.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "witcher4.jar"]