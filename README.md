# M1_Projet_DevOps

//TODO Mise en page markdown

### 1- Badges

[![Build Status](https://travis-ci.com/MaximeDeus/M1_Projet_DevOps.svg?branch=master)](https://travis-ci.com/MaximeDeus/M1_Projet_DevOps)


### 1- Présentation de l'ensemble des fonctionnalités

##### Représentation d'un Dataframe

Un Dataframe est ici représenté par une liste de HashMap (structure de données proche d'un dictionnaire en Python).
Une HashMap, qui correspond à une ligne, possède autant de clés qu'il existe de colonne dans le dataframe.
Ainsi, si l'on souhaite accéder à une valeur précise, il faut l'index de la ligne ainsi que le label de la colonne souhaitée.

Cette représentation est l'une parmi les nombreuses disponibles sur la bibliothèque Panda. Elle est lourde mais robuste.
L'autre représentation qui fut envisagée pour ce projet est celle utilisée par le premier constructeur (voir plus bas).

##### Création d'un Dataframe
Notre service permet de créer des Dataframes de deux façons différentes.

1. La première façon est le passage en paramètre d'une structure de données, ici une Hashmap.
Chaque clé correspond à une colonne. La valeur associée est la liste des valeurs à insérer dans le Dataframe.

- La seconde façon de créer un dataframe est le passage d'un fichier csv. Un exemple est disponible dans le dossier ressources du projet.

##### Affichage d'un Dataframe

Un Dataframe peut être affiché intégralement, ou selon deux stratégies :

1. Afficher les n premières lignes d'un dataframe.
- Afficher les n dernières lignes d'un dataframe.

##### Sélections dans un Dataframe
Deux types de sélection sont possibles

1. La sélection selon un intervalle de lignes (iloc)
- La sélection selon une liste de colonnes (loc)

Ces deux types de sélection créent un nouveau Dataframe (sous-ensemble).

##### Statistiques sur un Dataframe
Les colonnes qui correspondent à un type numérique peuvent renvoyer :

1. Le minimum
- Le maximum
- La somme
- La moyenne

//Cf sect 4.3
### 2- Présentation des outils utilisés

//TODO sect 4.3 listing des outils utilisés, justifier les choix effectués ; Si partie Docker réalisée, lister les différentes images chacunes accompagnées d'une courte description



//Cf 3.3
3- Mode d'emploi

//TODO Manuel d'utilisation des services

4- FeedBack

//TODO Retour sur les différents outils utilisés durant ce projet
