
FROM openjdk:17-bullseye

WORKDIR /app
# TODO: Copy the compiled jar -> execute ./gradlew clean build in terminal, then look in grandle build  dir,2 parameter
COPY build/libs/L2PB01-SpringBoot-and-Docker-1.0.0-plain.jar app.jar
# TODO: Copy the start.sh script, hier gibt es 2 parameter
COPY start.sh start.sh
# TODO: Make start.sh executable
RUN chmod 770 start.sh
# TODO: Set the start command
CMD ./start.sh