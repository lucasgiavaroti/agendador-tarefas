FROM gradle:9.2.1-jdk17 AS BUILD

WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM eclipse-temurin:17

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/agendador-tarefas.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/agendador-tarefas.jar"]
