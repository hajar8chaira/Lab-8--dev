#  Lab 8 — Web Service PHP & Application Android (Volley + Gson)

---



# Architecture complète : **MySQL + PHP (MVC) + Android (Volley + Gson)**

---

#  Partie 1 — Création de la base de données MySQL

##  Création de la base de données

```sql
CREATE DATABASE school1;
```

---

##  Création de la table `Etudiant`

```sql
CREATE TABLE Etudiant (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(50),
  prenom VARCHAR(50),
  ville VARCHAR(50),
  sexe VARCHAR(10)
);
```
<p align="center"> <img src="images/a1.png" width="800"> </p>
<p align="center"> <img src="images/a2.png" width="600"> </p>


---

## 5️⃣ Insertion de données tests

<p align="center"> <img src="images/a2.png" width="600"> </p>

✔ La base est prête pour le Web Service.

---

#  Partie 2 — Développement du Web Service PHP

## Structure du projet (VS Code)

<p align="center"> <img src="images/a16.png" width="600"> </p>
---

## Architecture utilisée (MVC simplifiée)

- **Model** → `Etudiant.php`
- **DAO** → `IDao.php`
- **Service** → `EtudiantService.php`
- **Connexion PDO** → `Connexion.php`
- **API Endpoints** → Dossier `ws`

---

##  Endpoints API

| Méthode | URL | Description |
|----------|------|------------|
| GET | /ws/loadEtudiant.php | Charger tous les étudiants |
| POST | /ws/createEtudiant.php | Ajouter |
| POST | /ws/deleteEtudiant.php | Supprimer |
| POST | /ws/updateEtudiant.php | Modifier |

---

## 🧪 Tests avec Postman

### ➕ Ajouter un étudiant

POST  
```
http://localhost/projet/ws/createEtudiant.php
```

Body → x-www-form-urlencoded :

```
nom = Chaira
prenom = Ayoub
ville = Marrakech
sexe = homme
```
<p align="center"> <img src="images/a4.png" width="600"> </p>
Réponse JSON :

<p align="center"> <img src="images/a5.png" width="600"> </p>
<p align="center"> <img src="images/a6.png" width="600"> </p>
---

### 📋 Charger la liste

GET :

```
http://localhost/projet/ws/loadEtudiant.php
```

Retour :

```json
[
  {
    "id": "1",
    "nom": "Chaira",
    "prenom": "Hajar",
    "ville": "Marrakech",
    "sexe": "femme"
  }
]
```

✔ Les tests Postman confirment le bon fonctionnement du backend.

---

# 🟢 Partie 3 — Application Android (Java + Volley + Gson)

## 📦 Technologies utilisées

- Java
- Volley (Requêtes HTTP)
- Gson (Parsing JSON)
- RecyclerView
- AlertDialog
- Architecture simple multi-activité

---

## 📱 Fonctionnalités implémentées

### ➕ 1. Ajouter un étudiant

- Formulaire (Nom, Prénom, Ville, Sexe)
- Envoi POST via Volley
- Parsing JSON avec Gson
- Redirection vers la liste

---

### 📋 2. Affichage de la liste

- RecyclerView
- Adapter personnalisé
- Chargement via GET
- Actualisation dynamique

---

### ✏️ 3. Modification

- Clic sur un étudiant
- Popup avec options
- Formulaire de modification
- Mise à jour via POST
- Refresh automatique

---

### 🗑️ 4. Suppression

- Popup confirmation
- Suppression via POST
- Mise à jour immédiate
- Toast de confirmation

---

# 🎯 Challenge final réalisé

✔ Activité affichant la liste complète  
✔ Popup Modifier / Supprimer  
✔ Confirmation avant suppression  
✔ Actualisation dynamique  
✔ Bouton retour vers AddEtudiant  
✔ Gestion propre des réponses JSON  

---

# 🔄 Fonctionnement global

```
Android App
      ↓ (HTTP - Volley)
Web Service PHP
      ↓ (PDO)
Base MySQL
```

Communication REST en JSON.

---

# 🧠 Analyse Technique

- Architecture MVC côté PHP
- Utilisation de PDO (sécurité + requêtes préparées)
- Communication REST
- Parsing JSON typé avec Gson
- RecyclerView dynamique
- Bonne gestion UX (AlertDialog + Toast)
- Synchronisation en temps réel

---

# 🚀 Résultat final

Application Android entièrement fonctionnelle avec :

- CRUD complet
- Synchronisation base de données
- Architecture propre
- Tests backend validés via Postman

---

# 👩‍💻 Auteur

**Hajar Chaira**  
EMSI — Développement Mobile  
Lab 8 — Web Service & Android
