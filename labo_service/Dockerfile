# Utiliser une image OpenJDK
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR généré par votre application Spring Boot
COPY target/labo_service-0.0.1-SNAPSHOT.jar app.jar


# Exposer le port utilisé par votre application
EXPOSE 8083

# Commande d'exécution
ENTRYPOINT ["java", "-jar", "app.jar"]
