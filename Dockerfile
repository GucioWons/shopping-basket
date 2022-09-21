FROM openjdk:17
EXPOSE 8081
ADD target/shopping-basket.jar shopping-basket.jar
ENTRYPOINT ["java", "-jar", "/shopping-basket.jar"]