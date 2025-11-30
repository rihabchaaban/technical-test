

# API de gestion d'utilisateur

##  Description

API REST développée avec Spring Boot permettant l'enregistrement et la gestion d'utilisateurs. Ce projet a été réalisé dans le cadre d'un test technique pour un stage.

### Fonctionnalités principales

- Enregistrement de nouveaux utilisateurs avec validation des données
- Récupération des informations d'un utilisateur par son identifiant
- Validation métier : seuls les utilisateurs majeurs résidant en France peuvent s'inscrire
- Journalisation automatique via AOP (Aspect-Oriented Programming)
- Base de données H2 en mémoire pour un démarrage rapide

##  Technologies utilisées

- **Java 17**
- **Spring Boot**
- **H2 Database** (base de données en mémoire)
- **Maven** (gestion des dépendances)
- **AOP** (journalisation)
- **JUnit & Mockito** (tests unitaires et d'intégration)

##  Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- Java 17 ou supérieur
- Maven 3.6 ou supérieur
- Un client REST (Postman)

##  Installation et démarrage

### 1. Cloner le projet

```bash
git clone https://github.com/rihabchaaban/technical-test.git
cd technical-test
```

### 2. Compiler le projet

```bash
mvn clean install
```

### 3. Lancer l'application

```bash
mvn spring-boot:run
```

L'API sera accessible à l'adresse : **http://localhost:8085**

##  Endpoints de l'API

### Enregistrer un utilisateur

**Endpoint :** `POST /api/users/register`

**Corps de la requête (JSON) :**

```json
{
  "username": "rihab",
  "birthdate": "2002-01-19",
  "country": "France",
  "phone": "0601234567",
  "gender": "FEMALE"
}
```

**Champs obligatoires :**
- `username` : Nom d'utilisateur
- `birthdate` : Date de naissance (format YYYY-MM-DD)
- `country` : Pays de résidence (doit être "France")

**Champs optionnels :**
- `phone` : Numéro de téléphone
- `gender` : Genre ( MALE,FEMALE,OTHER)

**Réponses :**
- `201 CREATED` : Utilisateur créé avec succès
- `400 BAD REQUEST` : Erreur de validation (utilisateur mineur, non résident français, ou données invalides)

### Récupérer un utilisateur

**Endpoint :** `GET /api/users/{id}`

**Réponses :**
- `200 OK` : Retourne les détails de l'utilisateur
- `404 NOT FOUND` : Utilisateur introuvable

**Exemple de réponse :**

```json
{
  "id": 3,
  "username": "rihab",
  "birthdate": "2002-01-19",
  "country": "France",
  "phone": "0601234567",
  "gender": "FEMALE"
}
```

##  Base de données

Le projet utilise **H2**, une base de données en mémoire qui ne nécessite aucune configuration externe.

### Accès à la console H2

La console H2 est disponible pour visualiser et interroger la base de données :

- **URL :** http://localhost:8085/h2-console
- **JDBC URL :** `jdbc:h2:mem:testdb`
- **Utilisateur :** `sa`
- **Mot de passe :** *(laisser vide)*

##  Journalisation (AOP)

Le projet intègre l'Aspect-Oriented Programming pour une journalisation automatique et transparente des appels aux contrôleurs.

### Informations journalisées

- Arguments d'entrée des méthodes
- Valeurs de retour
- Temps d'exécution de chaque méthode

Cette approche permet de séparer la logique de journalisation du code métier, facilitant ainsi la maintenance et le débogage.

**Exemple de log :**

```
>>>>>> Entering: ResponseEntity com.example.test.controller.UserController.createUser(UserRequest)
>>>>>> Arguments: [UserRequest{username='Rihab', birthdate=2002-01-19, country='France', phone='0601234567', gender=FEMALE}]
Hibernate: 
    insert 
    into
        users
        (birthdate, country, gender, phone, username, id) 
    values
        (?, ?, ?, ?, ?, default)
>>>>>> Exiting: ResponseEntity com.example.test.controller.UserController.createUser(UserRequest)
>>>>>> Result: <201 CREATED Created,UserResponse{id=1, username='Rihab', birthdate=2002-01-19, country='France', phone='0601234567', gender=FEMALE},[]>
>>>>>> Duration: 106 ms
```

##  Tests

Le projet dispose d'une couverture de tests complète pour garantir la qualité et la fiabilité du code.

### Tests d'intégration

Les tests d'intégration utilisent **MockMvc** pour simuler des requêtes HTTP réelles et valider le comportement complet de l'API.

**Scénarios testés :**
- Création réussie d'un utilisateur adulte français
- Rejet d'un utilisateur mineur
- Rejet d'un utilisateur non résident français
- Récupération d'un utilisateur existant
- Gestion des erreurs 404 pour les utilisateurs inexistants

### Tests unitaires

Les tests unitaires ciblent la logique métier du service `UserServiceImpl` en utilisant **Mockito** pour isoler les dépendances.

**Cas testés :**
- Validation des règles métier (âge, résidence)
- Gestion des exceptions
- Comportement du repository mocké

### Lancer les tests

```bash
mvn test
```



##  Règles de validation

L'API applique les règles métier suivantes :

1. **Âge minimum :** L'utilisateur doit être majeur (18 ans ou plus)
2. **Résidence :** Seuls les résidents français peuvent s'inscrire
3. **Champs obligatoires :** username, birthdate et country doivent être fournis

Toute violation des règles metiers entraîne un retour HTTP 400 avec un message d'erreur explicite.

##  Auteur

**Rihab Chaabane**

- GitHub : [@rihabchaaban](https://github.com/rihabchaaban)
