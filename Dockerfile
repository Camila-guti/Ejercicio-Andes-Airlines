FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# Dar permisos de ejecución a Maven Wrapper
RUN chmod +x mvnw

# Construir el proyecto
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/checkin-0.0.1-SNAPSHOT.jar"]
