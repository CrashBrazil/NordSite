FROM openjdk:24

COPY target/NORD-0.0.1-SNAPSHOT.jar NORD-1.0.0.jar


ENTRYPOINT ["java", "-jar", "NORD-1.0.0.jar"]
