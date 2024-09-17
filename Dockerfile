FROM openjdk:11
WORKDIR /app
COPY fq.jar /app
CMD ["java", "-jar", "fq.jar"]