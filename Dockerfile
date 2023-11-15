FROM openjdk:11
EXPOSE 8089
ADD target/Kaddem-2.7.5.jar Kaddem-2.7.5.jar
ENTRYPOINT ["java","-jar","/Kaddem-2.7.5.jar"]
