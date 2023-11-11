FROM openjdk:8
EXPOSE 8082
ADD target/mejrimayssa_g5_kaddem.jar mejrimayssa_g5_kaddem.jar
ENTRYPOINT ["java","-jar","/mejrimayssa_g5_kaddem.jar"]
