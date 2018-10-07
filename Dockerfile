FROM anapsix/alpine-java:8
RUN apk add --no-cache curl
COPY ./build/libs/*SNAPSHOT.jar app.jar
COPY ./entrypoint.sh /entrypoint.sh
EXPOSE 8082
#HEALTHCHECK --interval=5s --timeout=3s \
#  CMD curl -v http://localhost:8082/health || exit 1
ENTRYPOINT ["sh", "entrypoint.sh"]
