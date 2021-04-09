FROM openjdk:8
ADD target/audit-severity.jar audit-severity.jar
EXPOSE 9092
ENTRYPOINT ["java","-jar","/audit-severity.jar"]