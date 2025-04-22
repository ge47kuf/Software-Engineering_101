
FROM openjdk:17-bullseye

WORKDIR /app
# TODO: Copy the compiled jar
COPY build/libs/L10PB01-Docker-and-REST-1.0.0-plain.jar app.jar
# TODO: Copy the start.sh script
COPY start.sh .
# TODO: Make start.sh executable
RUN chmod 770 start.sh
# TODO: Set the start command
CMD ./start.sh