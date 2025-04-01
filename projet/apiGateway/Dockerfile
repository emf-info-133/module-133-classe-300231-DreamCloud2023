# Étape 1 : Utiliser une image Maven pour construire l'application
FROM openjdk:17-jdk-alpine AS build

# Définir le répertoire de travail
WORKDIR /app

RUN apk update && apk add maven

# Copier le fichier pom.xml pour télécharger les dépendances
COPY apigateway/pom.xml /app/pom.xml

# Télécharger les dépendances Maven sans lancer le build (permet d'éviter de re-télécharger les dépendances à chaque build)
RUN mvn dependency:go-offline

# Copier le code source de l'application dans le container
COPY apigateway/src /app/src

# Construire le fichier .jar à partir du projet
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image Java pour exécuter l'application
FROM openjdk:17-jdk-slim

# Définir un répertoire pour stocker l'application
WORKDIR /app

# Copier le fichier .jar de l'étape de build vers le répertoire courant
COPY --from=build /app/target/apigateway-0.0.1-SNAPSHOT.jar /app/app.jar

# Exposer le port sur lequel l'application va écouter
EXPOSE 8080

# Lancer l'application avec les options nécessaires
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
