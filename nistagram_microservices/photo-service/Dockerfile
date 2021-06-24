FROM openjdk:8
ADD target/docker-photo-service.jar docker-spring-boot.jar
EXPOSE 8087
CMD["java", "-jar", "docker-photo-service.jar"]