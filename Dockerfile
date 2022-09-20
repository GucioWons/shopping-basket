FROM openjdk:17
EXPOSE 8080
ADD target/shopping-basket.jar shopping-basket.jar
ENTRYPOINT ["java", "-jar", "/shopping-basket.jar"]