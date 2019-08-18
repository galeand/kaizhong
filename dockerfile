FROM 99taxis/mini-java8

ADD ./build/libs/*.jar kaizhong-1.0.1-SNAPSHOT.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "kaizhong-1.0.1-SNAPSHOT.jar"]
