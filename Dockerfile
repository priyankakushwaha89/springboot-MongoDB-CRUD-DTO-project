FROM openjdk:17

ADD target/Hostelfile.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]