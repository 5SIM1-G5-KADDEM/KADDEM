FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/azizataboubi_g5_5sim1-1.0.jar azizataboubi_g5_5sim1-1.0.jar
ENTRYPOINT ["java","-jar","/azizataboubi_g5_5sim1-1.0.jar"]