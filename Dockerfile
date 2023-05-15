FROM maven:3.8-openjdk-17 as builder

ENV TARGET=/home/usr/spotify
RUN mkdir -p $TARGET
RUN mkdir -p /var/lib/spotify_clone
WORKDIR $TARGET

COPY . $TARGET

RUN ["mvn", "package", "-DskipTests"]

FROM ubuntu:22.04

RUN ["apt-get", "update"]
RUN ["apt-get", "install", "openjdk-17-jre", "-y", "--no-install-recommends"]

COPY --from=builder /home/usr/spotify/target/spotify_clone-0.0.1-SNAPSHOT.jar /usr/local/spotify/spotify.jar

WORKDIR /usr/local/spotify
CMD ["java","-jar","spotify.jar"]
