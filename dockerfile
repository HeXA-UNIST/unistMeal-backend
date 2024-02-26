# 1. 빌드 단계
FROM gradle:latest AS build
WORKDIR /app
COPY . /app
RUN gradle build

# 2. 실행 단계
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]