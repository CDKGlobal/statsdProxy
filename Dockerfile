FROM maven:3.2-jdk-8-onbuild
MAINTAINER brmakana
EXPOSE 8080
ENV CATALINA_OPTS -Djava.security.egd=file:/dev/./urandom
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/statsdproxy-1.0.war"]