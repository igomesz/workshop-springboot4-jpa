FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
COPY . .
RUN apk add --no-cache maven && mvn clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/course-0.0.1-SNAPSHOT.jar"]