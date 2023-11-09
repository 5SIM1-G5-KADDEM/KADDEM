FROM openjdk:8
EXPOSE 8082
ADD target/kaddem.jar kaddem.jar
ENTRYPOINT ["java","-jar","/kaddem.jar"]
