FROM maven:3.8.1-ibmjava-8-alpine AS mavenService
WORKDIR /usr/src/server
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=mavenService /usr/src/server/target/zuul-api-gateway-0.0.1-SNAPSHOT.jar ./
EXPOSE 8079
CMD java -jar zuul-api-gateway-0.0.1-SNAPSHOT.jar