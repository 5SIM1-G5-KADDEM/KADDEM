FROM openjdk:8
EXPOSE 8082
ADD target/mohamedjasserbensmida_g5_kaddem.jar mohamedjasserbensmida_g5_kaddem.jar
ENTRYPOINT ["java","-jar","/mohamedjasserbensmida_g5_kaddem.jar"]
