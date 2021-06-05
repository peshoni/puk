FROM openjdk:8-jdk-alpine
RUN apk add --no-cache maven
WORKDIR /
COPY . .
RUN mvn install
#COPY --from=build /home/app/target/pwc.jar /pwc.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","./target/pwc.jar"]
