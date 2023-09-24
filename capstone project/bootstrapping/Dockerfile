FROM openjdk:17-jdk-alpine
RUN apk add maven
COPY . .
RUN mvn clean install -DskipTests
CMD ["java","-jar","target/bootstrapping-0.0.1-SNAPSHOT.jar"]