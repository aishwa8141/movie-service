FROM openjdk:10-jdk
ADD /target/movie-service-0.0.1-SNAPSHOT.jar /docker/app/movie-service-0.0.1-SNAPSHOT.jar
WORKDIR /docker/app
ENTRYPOINT ["java","-jar", "movie-service-0.0.1-SNAPSHOT.jar"]