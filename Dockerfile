FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/Kaddem-1.0.jar Kaddem-1.0.jar
ENTRYPOINT ["java","-jar","/Kaddem-1.0.jar"]
