# Build stage

FROM openjdk:17-alpine as build

WORKDIR /app

COPY . /app

RUN ["chmod", "+x", "gradlew"]

RUN ["./gradlew", "build", "-x", "test"]

# Production stage

FROM openjdk:17-alpine as production

WORKDIR /app

COPY --from=build ./app/build/libs/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]