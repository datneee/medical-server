FROM openjdk:17
ADD ./medical-server.jar medical-server.jar
ENTRYPOINT ["java", "-jar", "medical-server.jar"]
