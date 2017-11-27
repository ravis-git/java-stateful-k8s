# Dockerfile based on official alpine based openjdk

FROM rpalakodeti/jre8

MAINTAINER Ravi Palakodeti <ravi.palakodeti@ge.com>

# create a working dir and copy jar
WORKDIR /app
RUN pwd && ls
COPY jar/java-stateful-k8s-0.0.1-SNAPSHOT.jar .
RUN pwd && ls

ENTRYPOINT ["java", "-jar", "java-stateful-k8s-0.0.1-SNAPSHOT.jar"]