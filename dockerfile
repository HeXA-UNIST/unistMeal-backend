# java version
FROM openjdk:11

# work dir
WORKDIR /app

# copy file
COPY . .

# port available
EXPOSE 8080

# run
RUN chmod -R 755 .

RUN ./gradlew bootJar

FROM openjdk:11
COPY --from=builder build/libs/*.jar app.jar



ENTRYPOINT ["java", "-jar", "/app.jar"]