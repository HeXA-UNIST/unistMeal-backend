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

RUN ./gradlew bootJar	#gradlew를 통해 실행 가능한 jar파일 생성

FROM adoptopenjdk/openjdk11	#베이스 이미지 생성
COPY --from=builder build/libs/*.jar app.jar	#build이미지에서 build/libs/*.jar 파일을 app.jar로 복사

ENTRYPOINT ["java", "-jar", "/app.jar"]