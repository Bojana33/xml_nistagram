FROM maven:3.8.1-ibmjava-8-alpine AS mavenService
WORKDIR /usr/src/server
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=mavenService /usr/src/server/target/validation-request-service-0.0.1-SNAPSHOT.jar ./
EXPOSE 8090
CMD java -jar validation-request-service-0.0.1-SNAPSHOT.jar