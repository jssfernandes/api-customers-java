####
# This Dockerfile is used in order to build a container that runs the Quarkus application in JVM mode
#
# Before building the docker image run:
# mvn package
#
# Then, build the image with:
# docker build -f Dockerfile -t spring .
#
# Then run the container using:
# docker run --name spring -i --rm -p 8081:8081 spring
#
# Start the docker in the background
# docker run -d --name spring -i --rm -p 8081:8081 spring
#
####

FROM adoptopenjdk/openjdk11:ubi
COPY /target/*.jar /usr/app/app.jar

WORKDIR /usr/app

EXPOSE 5000

ENTRYPOINT ["java", "-Dspring.profiles.active=${profile}","-Duser.timezone=America/Sao_Paulo", "-jar", "app.jar"]