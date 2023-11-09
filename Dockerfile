FROM openjdk:8
EXPOSE 8082
ADD C:/Users/jasser/Documents/GitHub/KADDEM/target/kaddem.jar /kaddem.jar
ENTRYPOINT ["java","-jar","/kaddem.jar"]
