FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]




#If you use Gradle, you can run it with the following command:

#docker build --build-arg JAR_FILE=build/libs/\*.jar -t springio/gs-spring-boot-docker .COPY

#If you use Maven, you can run it with the following command:

#docker build -t springio/gs-spring-boot-docker .