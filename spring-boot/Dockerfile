FROM openjdk:jre-alpine

COPY target/rest-api-spring-boot-1.0-SNAPSHOT.jar /rest-api-spring-boot-1.0-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx64m", "-Xss256k", "-jar",  "/rest-api-spring-boot-1.0-SNAPSHOT.jar"]
