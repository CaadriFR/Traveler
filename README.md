
# **Voyageur de Commerce**

## **Introduction**

Ce projet consiste à modéliser un **écosystème de recherche** composé d’étudiants, chercheurs et maîtres de conférences (MCF) travaillant dans différentes disciplines. 
Il permet une **sélection personnalisée** des personnes de cet écosystème selon des critères spécifiques et vise à **résoudre le problème du voyageur de commerce** pour organiser des déplacements optimisés entre les villes où résident ces acteurs, en **minimisant la distance totale** parcourue.

---

## **Prérequis**

### **Environnement**
- **Java 17** ou version supérieure
- **Eclipse IDE** (recommandé)
- **PostgreSQL** installé et configuré

### **Bibliothèques**
- [`postgresql-42.7.4.jar`](https://jdbc.postgresql.org/download/) : Bibliothèque JDBC pour la connexion à PostgreSQL.
- **JUnit 5** : Pour les tests unitaires.

---

## **Configuration**

### **Fichier de Configuration**
Le fichier `Config.java` (package `fr.traveler.config`) centralise les paramètres du projet.

#### **Connexion à la Base de Données**
Spécifiez vos paramètres de connexion dans le fichier config (le programme ne peut pas fonctionner sans la base de donnée) :
```java
public static final String URL = "jdbc:postgresql://localhost:5432/projet_bdd";
public static final String USER = "postgres";
public static final String PASSWORD = "motdepasse";
```
La base de données doit contenir une table `villes_france_free` avec au minimum les colonnes suivantes (et leurs types correspondants) pour que le programme puisse récupérer les informations nécessaires sur les villes :

| Colonne              | Type          | Description                          |
|----------------------|---------------|--------------------------------------|
| `ville_nom_reel`     | `String` | Nom réel de la ville                |
| `ville_code_postal`  | `String`| Code postal de la ville             |
| `ville_population_2012` | `int`      | Population estimée en 2012          |
| `ville_surface`      | `double`       | Superficie de la ville (en km²)     |
| `ville_departement`  | `String`  | Département auquel appartient la ville |
| `ville_latitude_deg` | `double`       | Latitude géographique en degrés     |
| `ville_longitude_deg`| `double`       | Longitude géographique en degrés    |

Il est conseillé d’utiliser les données disponibles pour les différentes villes de France, accessibles sur la page [sql.sh](https://sql.sh/736-base-donnees-villes-francaises). Cette base de données, réalisée par Tony Archambeau et distribuée sous licence Creative Commons, garantit un **bon fonctionnement du programme** en fournissant des informations complètes sur les villes.

#### **Paramètres de l’Algorithme Génétique**

| Paramètre                     | Description                                   | Valeur par défaut |
|-------------------------------|-----------------------------------------------|-------------------|
| `MUTATION`                   | Probabilité de mutation (0 à 1).              | `0.1`            |
| `ELITISM`                    | Proportion des meilleurs individus gardés.    | `0.2`            |
| `HEURISTIC_INITIALISATION`   | Initialisation heuristique (0 à 1).           | `0.0`            |
| `SIZE_POPULATION`            | Taille de la population d'individus.          | `500`            |
| `MAX_ITERATIONS`             | Nombre maximal d’itérations.                  | `5000`           |
| `MAX_STAGNATION`             | Détection de stagnation du fitness.           | `1000`           |

#### **Affichage et Sauvegarde**
- **`DISPLAY_SOLUTION`** : Afficher la carte et le graphique du fitness (`true`/`false`).
- **`SOLUTION_TO_FILE`** : Enregistrer la solution dans un fichier (`true`/`false`).
- **`SOLUTION_PATH`** : Chemin pour le fichier `solution.txt`.

---

## **Compilation et Exécution**

### **Compilation**
1. Importez le projet dans **Eclipse**.  
2. Ajoutez les bibliothèques nécessaires au projet :
   - **`postgresql-42.7.4.jar`** : (clic droit sur le projet > Properties > Java Build Path > Libraries > Add External JARs).
   - **JUnit 5** : (clic droit sur le projet > Properties > Java Build Path > Libraries > Add Library > JUnit).
3. Ajoutez le dossier **`resources`** au Build Path :  
   - Faites un clic droit sur le dossier **`resources`** dans l’explorateur de projets > **Build Path** > **Use as Source Folder**.

Une fois ces étapes effectuées, le projet pourra être compilé et exécuté sans erreur directement depuis Eclipse.

### **Exécution**
Exécutez la méthode `main()` située dans la classe **Main** (package `fr.traveler`).

---

## **Utilisation**

Une fois le programme lancé, l’utilisateur interagit via deux menus principaux :

### **1. Menu de Gestion de l’Écosystème**
```plaintext
--- Ecosystem Management Menu ---
1. Generate the default ecosystem
2. View the entire ecosystem
3. Add a student
4. Add an MCF
5. Add a Researcher
6. Go to Hamiltonian Cycle Menu
7. Exit
```
- **Génération** et **modification** des données (ajouter des étudiants, MCF, chercheurs).  
L'écosystème par défaut contient des étudiants variés dans des villes qui couvrent une grande partie de la France, si les villes de certains étudiants ne se trouvent pas dans votre base de données, ils seront ignorés et pas ajoutés à l'écosystème.
- **Visualisation** de l’ensemble de l’écosystème.

### **2. Menu de Sélection et Résolution du Cycle Hamiltonien**
```plaintext
--- Hamiltonian Cycle Menu ---
1. Cycle to visit students in a given discipline
2. Cycle to visit researchers over 55 years old
3. Cycle to visit everyone
4. Custom cycle
5. Go to Ecosystem Management Menu
```

Le cycle personnalisé permet de :
1. **Choisir le type de personne** : Student, Titular, MCF, Researcher ou Everyone.
2. **Appliquer des filtres supplémentaires** :
   - Par Âge
   - Par Discipline
   - Par Régions
3. Trier :
   - Par année de thèse (uniquement si le type choisi est **Student**).
   - Par titulaires encadrant des étudiants (uniquement si le type choisi est **Titular**, **MCF**, ou **Researcher**).

---

## **Résultats**

- **Console** : Affiche l’itinéraire optimisé et les détails de la solution.
- **Carte et graphique** *(si activés)* :
   - Carte modélisant l’itinéraire trouvé.
   - Graphique montrant l’évolution du fitness pendant les itérations de l’algorithme génétique.
- **Fichier `solution.txt`** *(si activé)* : La solution est sauvegardée au chemin spécifié dans `Config.java`.
