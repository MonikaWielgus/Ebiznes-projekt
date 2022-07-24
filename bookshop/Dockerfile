FROM ubuntu:22.04 AS bookshop

RUN apt update
RUN apt-get install -y gradle default-jdk

COPY . /gradle/src
WORKDIR /gradle/src
RUN gradle build

RUN chmod +x gradlew

EXPOSE 8080:8080
CMD ./gradlew run