# M1_Projet_DevOps [![Build Status](https://travis-ci.com/MaximeDeus/M1_Projet_DevOps.svg?branch=master)](https://travis-ci.com/MaximeDeus/M1_Projet_DevOps) [![codecov](https://codecov.io/gh/MaximeDeus/M1_Projet_DevOps/branch/master/graph/badge.svg)](https://codecov.io/gh/MaximeDeus/M1_Projet_DevOps) [![Known Vulnerabilities](https://snyk.io/test/github/MaximeDeus/M1_Projet_DevOps/badge.svg?targetFile=projet.devops.deroissart.defours%2Fpom.xml)](https://snyk.io/test/github/MaximeDeus/M1_Projet_DevOps?targetFile=projet.devops.deroissart.defours%2Fpom.xml)

### 1- Représentation choisie pour un Dataframe

Un Dataframe est ici représenté par une liste de dictionnaires.
Chaque dictionnaire correspond donc à une ligne, et possède autant d'entrées (aussi appelées clés) qu'il existe de colonne.
Ainsi, si l'on souhaite accéder à une valeur précise, il faut l'index (le numéro) de la ligne ainsi que le nom (label) de la colonne souhaitée.
Cette représentation est l'une parmi les nombreuses disponibles sur la bibliothèque Panda. 
Elle est lourde (instantiation de chaque dictionnaire), mais permettrait dans une version ultérieure de la bibliothèque de réduire la complexité des opérations de suppression (plus d'infos sur https://blog.xebia.fr/2011/02/17/java-collection-performance/).


### 2- Présentation de l'ensemble des fonctionnalités

##### Création d'un Dataframe
Notre service permet de créer des Dataframes de deux façons différentes.

1. La première façon est le passage en paramètre d'une structure de données, ici un dictionnaire.
Chaque clé correspond à une colonne, représentée par un tableau.

2. La seconde façon de créer un dataframe est le passage d'un fichier csv. Un exemple est disponible dans le dossier ressources du projet.

##### Affichage d'un Dataframe

Un Dataframe peut être affiché intégralement, ou selon deux stratégies :

1. Afficher les n premières lignes d'un dataframe.
2. Afficher les n dernières lignes d'un dataframe.

##### Sélections dans un Dataframe
Deux types de sélection sont possibles

1. La sélection selon un intervalle de lignes (iloc)
2. La sélection selon une liste de colonnes (loc)

Ces deux types de sélection créent un nouveau Dataframe (sous-ensemble).

##### Statistiques sur un Dataframe
Les colonnes qui correspondent à un type numérique peuvent renvoyer :

1. Le minimum
2. Le maximum
3. La somme
4. La moyenne

### 3- Présentation des outils utilisés

Le projet est basé sur une architecture Maven
Pour les badges, nous avons utilisé les services suivants :

1. Travis CI pour l'intégration (execution d'un script dans une VM)
2. CodeCov (100% en couverture de test)
3. Snyk (Dependency Security Vulnerability Checking)

Différentes bibliothèque ont été utilisées :
1. JUnit4 pour les tests
2. Google catch exception pour tester les exceptions dans des containers
3. OpenCSV pour lire un fichier csv (posait problème sur Travis avec la lib java.util.Scanner)

### 4- Mode d'emploi

S'agissant d'une bibliothèque d’analyse de données, le programme ne possède pas de fonction principale.
Il s'agit donc ici d'exécuter les tests et générer la documentation. 

Pour exécuter les tests, executer la commande suivante depuis le dossier projet.devops.deroissart.defours :
$ mvn test

Pour générer la documentation, executer la commande suivante depuis le dossier projet.devops.deroissart.defours :
$ mvn javadoc:javadoc

Pour nettoyer le projet, executer la commande suivante depuis le dossier projet.devops.deroissart.defours :
$ mvn clean

### 5- FeedBack

Nous avons appris pas mal de choses, que d'ailleurs nous aurions du mettre en place plus tôt,
comme par exemple les badges ainsi que l'utilisation d'outils qui permettent de vérifier et déployer du code à chaque mise en ligne.
