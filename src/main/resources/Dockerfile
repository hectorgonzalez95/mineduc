FROM frolvlad/alpine-oraclejdk8:slim
RUN apk add -U tzdata
RUN cp /usr/share/zoneinfo/America/Santiago /etc/localtime
RUN date
VOLUME /opt/proyectos/nfsshared/induccion
RUN mkdir -p /induccion/lib/
RUN mkdir -p /induccion/config/
RUN mkdir -p /induccion/logs/
ADD induccion.jar /induccion/lib/app.jar
ADD bootstrap.yml /induccion/config
ADD logback-spring.xml /induccion/config
ADD application-induccion-messages.properties /induccion/config
WORKDIR /induccion
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","lib/app.jar"]
EXPOSE 8878
