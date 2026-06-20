FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /workspace

COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY src ./src

RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon -x test

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring

ENV SPRING_PROFILES_ACTIVE=uat

COPY --from=build /workspace/build/libs/*.jar app.jar

USER spring:spring

ENTRYPOINT ["sh", "-c", "exec java -Dserver.port=$PORT -Dserver.address=0.0.0.0 -jar app.jar"]