FROM adoptopenjdk:11-jdk-hotspot
EXPOSE 8082
ADD target/mejrimayssa_g5_kaddem.jar mejrimayssa_g5_kaddem.jar
ENTRYPOINT ["java","-jar","/mejrimayssa_g5_kaddem.jar"]
