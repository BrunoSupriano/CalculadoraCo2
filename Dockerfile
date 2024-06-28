FROM openjdk:22
COPY /target/calculadoraco2-2.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]
