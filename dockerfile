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

# execute
ENTRYPOINT ./gradlew unistMeal-backend:bootRun -Penvironment="production"