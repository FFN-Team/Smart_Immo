FROM openjdk:21
VOLUME /tmp
EXPOSE 9001
COPY smartimmo/target/smartimmo-*.jar /usr/local/smartimmo.jar
ENTRYPOINT ["java","-jar","/usr/local/smartimmo.jar"]
