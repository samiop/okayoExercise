Ce README explique comment lancer ma solution pour l’exercice Spring Boot.
Technologies utilisées :
Spring Boot + Postman pour le test du CRUD + PostgreSQL

Voici la structure de mon projet, où l’on distingue le dossier controller qui contient tous les contrôleurs de nos entités, le dossier model qui comprend nos classes (entités) et le dossier repositories (qui contient l’interaction avec la base de données pour chaque entité).


<img width="286" height="840" alt="image" src="https://github.com/user-attachments/assets/ba69a995-083c-4cf3-a487-af040806a3cb" />

Si vous avez PostgreSQL déjà installé, vous pouvez passer à l’étape 2.

1) Pour installer PostgreSQL, veuillez lancer la commande winget install -e --id PostgreSQL.PostgreSQL.16.
Si cela ne marche pas, essayez de trouver le paquet disponible avec la commande : winget search postgresql, puis relancez la commande précédente.

2) Ouvrez une ligne de commande en administrateur, puis
git pull <lien>

Pour lancer le projet :
mvnw.cmd spring-boot:run
Puis, si vous êtes sur Visual Studio Code, installez l’extension Connect to Server, après quoi accédez à la base de données en renseignant le username, le password et le database name indiqués dans le fichier application.properties.



<img width="1010" height="647" alt="image" src="https://github.com/user-attachments/assets/d1e0a216-54a9-47f7-a2bb-6d4af8e5db0c" />


Ensuite, si vous voulez créer, modifier et récupérer des clients, produits et factures, vous pouvez le faire via http://localhost:8080/....
Exemple :

<img width="1400" height="571" alt="image" src="https://github.com/user-attachments/assets/bc7dad6a-2c7c-4025-945b-b011d36797e6" />


Amélioration : 
1) on peut rajouter swagger UI pour une meilleure visualisation des api's  
2) rajouter une interface graphique avec react pour la mise en place d'une facture et ses lignes de facture 







Et voilà.
