FROM ubuntu:jammy
# Install basic programs
RUN apt-get update -y && apt-get install -y git curl zip
# Install sdkman for gradle installation
RUN curl -s "https://get.sdkman.io" | bash

# Install gradle
SHELL ["/bin/bash", "-c"] 
RUN source "/root/.sdkman/bin/sdkman-init.sh" && \
    sdk install gradle 8.3

# Install JAVA
RUN apt-get install -y openjdk-11-jdk

# Install SQlite3
RUN apt-get install -y sqlite3

RUN git clone https://github.com/HeXA-UNIST/unistMeal-backend
#! remove this
RUN source "/root/.sdkman/bin/sdkman-init.sh" && cd unistMeal-backend && \
    git checkout dev && \ 
    cd MealU_HeXA_Project && \
    chmod +x gradlew && \
    gradle wrapper && \
    ./gradlew && \
    gradle clean build bootJar
RUN cp `find /unistMeal-backend/MealU_HeXA_Project/build/libs -name "*[0-9].jar"` /unistMeal.jar

EXPOSE 8080
WORKDIR /unistMeal-backend
ENTRYPOINT ["java", "-jar", "/unistMeal.jar"]
