FROM openjdk:11.0-jre-slim
ARG JAR_FILE=build/libs/*.jar
RUN adduser app
USER app:app
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
