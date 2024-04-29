FROM public.ecr.aws/amazoncorretto/amazoncorretto:21

COPY smartimmo/target/smartimmo-*.jar /usr/local/smartimmo.jar

EXPOSE 9001
ENTRYPOINT java -jar /usr/local/smartimmo.jar