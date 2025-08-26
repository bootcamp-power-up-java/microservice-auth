FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY *.jar MicroserviceAuth.jar
ENV JAVA_OPTS=" -Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
# Crear el usuario no-root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar MicroserviceAuth.jar" ]
