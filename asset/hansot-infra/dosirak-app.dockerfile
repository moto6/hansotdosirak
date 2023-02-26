FROM arm64v8/openjdk:11.0.11-9 as jenv
COPY ../../gradlew .
COPY ../../gradlev gradle
COPY ../../build.gradle .
COPY ../../settings.gradle .
COPY ../../src src
RUN ./gradlew clean
RUN ./gradlew -x test build
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} .jar
ENTRYPOINT ["java", "-jar", "app.jar"]