FROM arm64v8/openjdk:11.0.11-9 as jenv
RUN ls && pwd

#COPY ./../../gradlew .
#COPY ./../gradlev gradle
#COPY ./../build.gradle .
#COPY ./../../settings.gradle .
#COPY ./../../src src

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src


RUN ./gradlew clean
RUN ./gradlew -x test build
ARG JAR_FILE=./build/libs/dosirak-0.1.jar
COPY ${JAR_FILE} app.jar
RUN export SPRING_PROFILES_ACTIVE=test
ENTRYPOINT ["java", "-jar", "-Dspring.config.activate.on-profile=test","app.jar"]