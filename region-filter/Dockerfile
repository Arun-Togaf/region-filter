# Use the Java 17 image as the base
FROM openjdk:17.0.1-jdk-slim

WORKDIR /app
COPY ./target/region-filter-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "region-filter-0.0.1-SNAPSHOT.jar"]