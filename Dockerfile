FROM amazoncorretto:21-alpine
COPY target/sales-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod", "/app.jar"]
