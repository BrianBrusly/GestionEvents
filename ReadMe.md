# GestionEvents

## 📌 Description
Ce projet permet de gérer différents types d'événements (**conférences, concerts**), avec :
- **Inscription des participants**
- **Gestion des organisateurs**
- **Notifications en temps réel (Observer)**
- **Persistance des données (JSON/XML)**

## 📂 Structure du projet
- `Evenement.java` : Classe abstraite des événements
- `Conference.java`, `Concert.java` : Sous-classes spécialisées
- `GestionEvenements.java` : **Singleton**
- `NotificationService.java` : **Interface pour l’envoi de notifications**
- `EvenementObservable.java` et `ParticipantObserver.java` : **Observer Pattern**
- `CapaciteMaxAtteinteException.java` et `EvenementDejaExistantException.java` : **Exceptions personnalisées**

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
│    │    │    │    ├── NotificationService.java
│    │    │    │    ├── GestionEvenements.java
│    │    ├── resources/
│    │    │    ├── Fichiers(.json)
│    │    ├── observer/
│    │    │    ├── EvenementObservable.java
│    │    │    ├── ParticipantObserver.java
│    ├── test/
│    │    ├── java/
│    │    │    ├── tests/
│    ├── docs/
│    │    ├── UML/
│    │    ├── Rapport.pdf
├── pom.xml (Si tu utilises Maven)
├── README.md (Explication du projet)
├── .gitignore (Liste des fichiers à ignorer)


---

## ⚙️ Technologies utilisées

- Java 21+
- Programmation orientée objet (POO)
- Git & GitHub

---

## 🚀 Fonctionnalités prévues

- [x] Modèle `Evenement` de base
- [x] Ajout des participants et organisateurs
- [ ] Gestion des notifications
- [ ] Interface utilisateur (console ou graphique)
- [ ] Sauvegarde et chargement des événements (JSON, fichier, base de données)

---

## 📁 À venir

- Tests unitaires
- Système d'inscription aux événements
- Support multilingue

---

## 👨‍💻 Auteur

> Développé par [BrianBrusly](https://github.com/BrianBrusly)

---

## 📜 Licence

Ce projet est sous licence libre (à préciser, ex : MIT, GPL...).


## 🛠️ Installation
1️⃣ Clonez le projet :
   ```sh
   git clone https://github.com/BrianBrusly/GestionEvents.git
