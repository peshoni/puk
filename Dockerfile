FROM openjdk:11-jdk
COPY ./target/${JAR_FILE} pwc.jar
ENTRYPOINT ["java","-jar","pwc.jar"]