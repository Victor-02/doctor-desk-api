FROM alpine:latest

WORKDIR /app

COPY .. .
RUN apk add openjdk17
RUN apk add maven

ENV DATASOURCE_URL=jdbc:postgresql://172.17.0.2:5432/doctordesk
ENV DATASOURCE_USERNAME=postgres
ENV DATASOURCE_PASSWORD=root

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
