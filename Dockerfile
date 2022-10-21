FROM openjdk:8

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/dormitory-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m",  "-jar", "/app/dormitory-0.0.1-SNAPSHOT.jar"]

EXPOSE 8111