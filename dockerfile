# Build stage

FROM gradle:latest as build

WORKDIR /app

COPY . /app

RUN ["chmod", "+x", "gradlew"]

RUN ["./gradlew", "build", "-x", "test"]

# Production stage

FROM openjdk:11-jre-slim as production

WORKDIR /app

COPY --from=build ./app/build/libs/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]