FROM openjdk:17-alpine
COPY ./target/Revagenda-0.3.jar /usr/app/
WORKDIR /usr/app
EXPOSE 7777
ENTRYPOINT ["java", "-jar", "Revagenda-0.3.jar"]
