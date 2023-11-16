FROM openjdk:11
EXPOSE 8089
#ADD target/Kaddem-2.7.5.jar Kaddem-2.7.5.jar
RUN curl -O http://192.168.33.10:8081/repository/maven-releases/tn/esprit/spring/kaddem/2.7.5/kaddem-2.7.5.jar

ENTRYPOINT ["java","-jar","/kaddem-2.7.5.jar"]
