FROM khipu/openjdk17-alpine
RUN addgroup -S spring && adduser -S varshini -G spring
USER varshini:spring
VOLUME /tmp
ARG JAR_FILE=target/receipt-processor-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /app/receipt-processor-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/receipt-processor-app.jar"]