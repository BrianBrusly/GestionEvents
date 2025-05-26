# GestionEvents

##  Description
Ce projet permet de gérer différents types d'événements (**conférences, concerts**), avec :
- **Inscription des participants**
- **Gestion des organisateurs**
- **Notifications en temps réel (Observer)**
- **Persistance des données (JSON)**

##  Structure du projet
- `Evenement.java` : Classe abstraite des événements
- `Conference.java`, `Concert.java` : Sous-classes spécialisées
- `GestionEvenements.java` : **Singleton**
- `NotificationService.java` : **Interface pour l'envoi de notifications**
- `EvenementObservable.java` et `ParticipantObserver.java` : **Observer Pattern**
- `CapaciteMaxAtteinteException.java` , `EvenementDejaExistantException.java` ...etc : **Exceptions personnalisées**

```
GestionEvents/
├── src/
│    ├── main/
│    │    ├── java/
│    │    │    ├── models/
│    │    │    │    ├── Evenement.java
│    │    │    │    ├── Conference.java
│    │    │    │    ├── Concert.java
│    │    │    │    ├── Participant.java
│    │    │    │    ├── Organisateur.java
│    │    │    │    ├── Intervenant.java
│    │    │    ├── service/
│    │    │    │    ├── NotificationService.java
│    │    │    │    ├── EmailNotificationService.java
│    │    │    │    ├── SMSNotificationService.java
│    │    │    ├── gestion/
│    │    │    │    ├── GestionEvenements.java
│    │    │    ├── persistence/
│    │    │    │    ├── GestionPersistance.java
│    │    │    ├── observer/
│    │    │    │    ├── EvenementObservable.java
│    │    │    │    ├── ParticipantObserver.java
│    │    │    ├── exceptions/
│    │    │    │    ├── CapaciteMaxAtteinteException.java
│    │    │    │    ├── EvenementDejaExistantException.java
│    │    │    │    ├── EvenementNonTrouveException.java
│    │    │    │    ├── ParticipantDejaInscritException.java
│    │    │    ├── factory/
│    │    │    │    ├── NotificationFactory.java
│    │    │    ├── Main.java
│    │    ├── resources/
│    │    │    ├── evenements.json
│    │    │    ├── participants.json
│    ├── test/
│    │    ├── java/
│    │    │    ├── tests/
│    │    │    │    ├── GestionEvenementsTest.java
│    │    │    │    ├── EvenementTest.java
│    │    │    │    ├── GestionPersistanceTest.java
├── pom.xml
├── README.md
├── .gitignore
```

##  Technologies utilisées

- Java 14+
- JavaFX pour l'interface graphique
- Jackson pour la sérialisation/désérialisation JSON
- JUnit 5 pour les tests unitaires
- Maven pour la gestion des dépendances et du build
- Programmation orientée objet (POO)

##  Design Patterns implémentés

- **Singleton** : Pour la classe GestionEvenements
- **Observer** : Pour la notification des participants
- **Factory** : Pour la création des services de notification
- **Strategy** : Pour les différents types de notification (Email, SMS)

##  Fonctionnalités

-  Modèle `Evenement` avec sous-types Concert et Conference
-  Gestion des participants et organisateurs
-  Système de notification en temps réel (Observer Pattern)
-  Persistance des données en JSON
-  Interface utilisateur JavaFX
-  Tests unitaires avec JUnit 5

##  Tests

Des tests unitaires complets sont inclus pour valider:
- La logique métier de GestionEvenements
- Les opérations sur les événements
- La persistance des données

## 🛠️ Installation

1. Clonez le projet :
   ```sh
   git clone https://github.com/BrianBrusly/GestionEvents.git
   ```

2. Importez le projet dans IntelliJ IDEA:
    - File > Open... > Sélectionnez le dossier du projet
    - IntelliJ devrait détecter automatiquement le fichier pom.xml

3. Construisez le projet avec Maven:
   ```sh
   mvn clean install
   ```

4. Exécutez l'application:
   ```sh
   mvn javafx:run
   ```

##  Principes SOLID appliqués

1. **Single Responsibility Principle (SRP)** : Chaque classe a une responsabilité unique.
2. **Open/Closed Principle (OCP)** : Les classes sont ouvertes à l'extension mais fermées à la modification.
3. **Liskov Substitution Principle (LSP)** : Les sous-classes (Concert, Conference) peuvent remplacer la classe de base (Evenement).
4. **Interface Segregation Principle (ISP)** : Des interfaces spécifiques (EvenementObservable, ParticipantObserver) sont utilisées.
5. **Dependency Inversion Principle (DIP)** : La dépendance vers les abstractions plutôt que les implémentations concrètes.

##  Auteur

> Développé par [BrianBrusly](https://github.com/BrianBrusly)
