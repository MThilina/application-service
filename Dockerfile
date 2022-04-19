FROM openjdk:8-jdk-alpine
ADD target/application-service.jar application-service.jar

#exposing ports
EXPOSE 8003
EXPOSE 80
EXPOSE 3306

ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV ACTIVATED_PROFILE=dev

ENV DB_URL=jdbc:mysql://localhost:3306/assignment_db
ENV DB_USER_NAME=admin
ENV DB_PASSWORD=1qaz@WSX3edcdocke

ENV ISSUER_URI=http://localhost:8080/auth/realms/dev
ENV JWK_SET_URI=http://localhost:8080/auth/realms/dev/protocol/openid-connect/certs

ENTRYPOINT ["java","-jar","application-service"]