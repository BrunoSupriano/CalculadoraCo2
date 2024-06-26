FROM openjdk:22-alpine
COPY /target/calculadoraco2-2.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
