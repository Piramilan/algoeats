#
# Build stage
#
FROM maven:3.8.3-openjdk-23 AS build
WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-alpine AS runtime
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
