FROM alpine:latest

WORKDIR /app

COPY .. .
RUN apk add openjdk17
RUN apk add maven

ARG DATASOURCE_IP

ENV DATASOURCE_URL=jdbc:postgresql://$DATASOURCE_IP:5432/doctordesk
ENV DATASOURCE_USERNAME=postgres
ENV DATASOURCE_PASSWORD=root

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
