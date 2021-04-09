FROM openjdk:8
ADD target/audit-auth.jar audit-auth.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/audit-auth.jar"]