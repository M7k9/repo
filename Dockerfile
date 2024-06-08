FROM openjdk:22
ARG JAR_FILE=target/*.jar
COPY ./target/otp-0.0.1-SNAPSHOT.jar OTP.jar
ENTRYPOINT ["java","-jar","/OTP.jar"]

