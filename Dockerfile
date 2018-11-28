FROM openjdk:11.0.1-oraclelinux7
EXPOSE 8080
ADD /build/libs/rememberallprovider-1.0.0-SNAPSHOT.jar rememberallprovider-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "rememberallprovider-1.0.0-SNAPSHOT.jar"]