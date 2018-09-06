# MiCorr-WebServices
Application Java mettant à disposition un service web d'interrogation d'une ontologie via 4 requêtes spécifiques. Cette application a été spécifiquement développée pour MiCorr.org

## Introduction

Pour le bon fonctionnement de l'application en local, télécharger une copie du projet sur votre machine.

### Prérequis

Afin de pouvoir exécuter l'application, il est nécessaire d'installer Docker sur la machine. En effet, cette application s'exécute sur un serveur de container Tomcat qui lui-même à besoin d'un serveur web sémantique Apache Jena Fuseki.

Pour toutes informations sur l'installation de Docker, vous pouvez vous rendre sur le [site officiel](https://www.docker.com/).

Le code source d'application a été réalise avec Eclispe et Maven. Toutes les dépendances sont indiquées dans le fichier pom.xml de l'application.

### Installation

Dans le but de lancer l'application, une fois le code téléchargé, il est nécessaire de taper la commande suivante dans la console :

```
docker-compose up
```

Cette commande sous-entend que vous vous trouvez à la racine du projet, où le fichier docker-compose.yml se situe.

Une fois cette commande exécutée, l'application est disponible via son URL.

## Tests
Afin de test le bon fonctionnement de l'application et en se basant sur la configuration actuelle, le service web est accessible à cette adresse:

[http://localhost:8888/MiCorr-WebServices/rest/artefacts/micorrArtefactList?text=Knife](http://localhost:8888/MiCorr-WebServices/rest/artefacts/micorrArtefactList?text=Knife)

Ce test nous permet d'interroger l'ontologie et de retourner les informations liées à la recherche `Knife`

Si tout c'est bien passé, le résultat de la recherche devrait être le suivant :

```JSON
{
   "corrosion" : {
      "artefact" : "",
      "id" : -1,
      "name" : "",
      "type" : ""
   },
   "country" : {
      "artefact" : "",
      "id" : -1,
      "name" : "",
      "type" : ""
   },
   "environment" : {
      "artefact" : "",
      "id" : -1,
      "name" : "",
      "type" : ""
   },
   "metal" : {
      "artefact" : "",
      "id" : -1,
      "name" : "",
      "type" : ""
   },
   "text" : {
      "artefact" : "",
      "assertions" : [ "Household implement", "Weapon" ],
      "id" : 30,
      "name" : "Knife",
      "parentsData" : [ "TypeOfObject" ],
      "sistersData" : [ "Oenochoe, vessel", "Tool", "Wedge part of an altar crown suspension in the abbey chapel", "Submarine part", "Aeroplane part", "Bed structure", "Iron (and steel) sheet", "Technical object", "Jewellery", "Household implement", "Metal sheet", "Military tag", "Situla", "Pin", "Headrest or horse bit", "Architectural element", "Weapon", "Not defined", "Horological object", "Military carriage" ],
      "type" : "artefacts_type"
   }
}
```

## Service Web

### Accès aux services web

Afin de pouvoir accéder aux webservices via une requête HTTP, l’URL de base est la suivante :

http://host/MiCorr-WebServices/rest/artefacts

En fonction du webservice, il sera nécessaire de compléter cette URL avec les données fournies dans la description du webservice.

### Liste des services web
`GET	getMiCorrArtefactList` (String text, String country, String metalFamily, String corrosionForms, String environments)

Retourne une liste des artefacts correspondants aux différents paramètres fournis dans la requête. Tous les paramètres de la requête sont optionnels.

### Description des services web
 `GET  getMiCorrArtefactList` (String text, String country, String metalFamily, String corrosionForms, String environments)
 
**URL**

Afin d’appeler ce webservice, il est nécessaire de compléter l’URL de base de ce webservice par :
/micorrArtefactList
Ainsi, l’URL complète sera :
http://host/MiCorr-WebServices/rest/artefacts/micorrArtefactList?text=monText&country=...

**Description** 

Retourne une liste des artefacts correspondants aux différents paramètres fournis dans la requête. Tous les paramètres de la requête sont optionnels.
 
**Paramètres** 

| Nom            | Description                                                |
| -------------- | ---------------------------------------------------------- |
| text           | Un texte libre à recherche dans l’ontologie                |
| country        | Un pays à rechercher dans l'ontologie                      |
| metalFamily    | Une famille de métal à rechercher dans l'ontologie         |
| corrosionForms | Une forme de corrosion à rechercher dans l'ontologie       |
| environments   | Un type d'environnement à rechercher dans l'ontologie      |

**Réponse** 

Un tableau au format JSON de tous les éléments liés aux artefacts fournis comme paramètre de la requête.

## Développé avec 

- Eclipse Oxygen
- Maven
- Docker

