# Build
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /home/app
COPY src ./src
COPY pom.xml ./
RUN mvn -f ./pom.xml clean package

# Run
FROM openjdk:11
COPY --from=build /home/app/target/reading-is-good-0.0.1-SNAPSHOT.jar /usr/local/lib/reading-is-good-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/reading-is-good-0.0.1-SNAPSHOT.jar"]