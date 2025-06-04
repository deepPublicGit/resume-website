FROM maven:3.9.9-eclipse-temurin-24 as build
COPY . /app
WORKDIR /app
RUN mvn clean package -Pproduction
RUN mv -f target/*.jar app.jar

FROM eclipse-temurin:24-jre
COPY --from=build /app/app.jar .
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]