#  Lab 8 — Web Service PHP & Application Android (Volley + Gson)

### Architecture complète : **MySQL + PHP (MVC) + Android (Volley + Gson)**

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

---

##  Insertion de données tests

<p align="center"> <img src="images/a2.png" width="800"> </p>

-La base est prête pour le Web Service.

---

#  Partie 2 — Développement du Web Service PHP

## Structure du projet (VS Code)

<p align="center"> <img src="images/a16.png" width="400"> </p>

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

##  Tests avec Postman

###  Ajouter un étudiant

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
<p align="center"> <img src="images/a4.png" width="800"> </p>


###  Charger la liste

GET :

```
http://localhost/projet/ws/loadEtudiant.php
```
<p align="center"> <img src="images/a5.png" width="800"> </p>
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
......
]
```

 Les tests Postman confirment le bon fonctionnement du backend.

---

#  Partie 3 — Application Android (Java + Volley + Gson)

## Test et vérification:
<p align="center"> <img src="images/a8.png" width="400"> </p>
<p align="center"> <img src="images/a9.png" width="1000"> </p>
---

##  Fonctionnalités implémentées

###  1. Ajouter un étudiant

- Formulaire (Nom, Prénom, Ville, Sexe)
<p align="center"> <img src="images/a6.png" width="600"> </p>
---

###  2. Affichage de la liste

- RecyclerView
- Adapter personnalisé
- Chargement via GET
- Actualisation dynamique
<p align="center"> <img src="images/a10.png" width="400"> </p>

---
###  3. Suppression

- Popup confirmation
- Suppression via POST
- Mise à jour immédiate
- Toast de confirmation
<p align="center"> <img src="images/a11.png" width="400"> </p>
<p align="center"> <img src="images/a12.png" width="400"> </p>
<p align="center"> <img src="images/a13.png" width="400"> </p>
---
###  3. Modification

- Clic sur un étudiant
- Popup avec options
- Formulaire de modification
- Mise à jour via POST
- Refresh automatique
<p align="center"> <img src="images/a14.png" width="400"> </p>
<p align="center"> <img src="images/a15.png" width="400"> </p>
---

---

#  Challenge final réalisé

1.  Activité affichant la liste complète  
2.  Popup Modifier / Supprimer  
3.  Confirmation avant suppression  
4.  Actualisation dynamique  
5.  Bouton retour vers AddEtudiant  
6.  Gestion propre des réponses JSON  

---

#  Fonctionnement global

```
Android App
      ↓ (HTTP - Volley)
Web Service PHP
      ↓ (PDO)
Base MySQL
```

---



