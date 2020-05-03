FROM gradle:6.3-jdk
ARG MODULE
USER root
RUN mkdir /build
COPY . /build
WORKDIR /build/${MODULE}
RUN gradle build -x test

FROM openjdk:8-jre-alpine
ARG MODULE
LABEL maintainer="vladimirshefer"
RUN mkdir /app
WORKDIR /app
COPY --from=0 "build/${MODULE}/build/libs/${MODULE}.jar" "/app/application.jar"
EXPOSE 8080
RUN echo "${MODULE}"
CMD java \
    -Dspring.profiles.active="${PROFILE:-production}"\
    -Xmx1024m \
    -jar "application.jar" \
    --server.port=8080
