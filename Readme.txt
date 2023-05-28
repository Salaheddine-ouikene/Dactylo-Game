Pour utiliser cette application, voici les prérequis: Eclipse ou Intelij, Java 18, les fichiers de librairies JavaFx 19 et des fichiers txt contenant des mots aléatoires (il n'y a pas de limite de mots). Voici comment lancer l'application:

    Ajoutez les librairies JavaFx en utilisant le menu Fichier > Structure du projet > Bibliothèques > Ajouter tous les fichiers javafx existants dans le dossier lib. N'oubliez pas d'ajouter ceci aux paramètres VM: java --module-path "chemin vers votre dossier de lib javafx" --add-modules javafx.controls,javafx.fxml VotreFichier.
    Placez le fichier txt nommé "wordlist" dans le dossier racine.
    Lancez l'application en utilisant le bouton Exécuter ou en utilisant la ligne de commande dans le dossier racine: java --module-path D:\javafx-sdk-19.0.2\lib --add-modules javafx.controls app.

Le code de ce projet comprend quatre éléments principaux: l'interface utilisateur, les règles du jeu, des animations et une fenêtre contextuelle pour afficher les scores et les résultats. Il y a également une option qui enregistrera votre vitesse maximale, votre précision et votre nombre de vies.

Voici comment utiliser cette application pour jouer:

    Lancer l'application en utilisant des commandes ou en utilisant un IDE.
    Si c'est la première fois que vous jouez, vous devrez saisir votre nom.
    Cliquez sur n'importe quel bouton pour accéder au jeu.
    Le jeu ne démarrera pas tant que vous n'aurez pas commencé à taper sur votre clavier. Chaque mot tapé correctement vous rapportera un point et vous ne perdrez pas de vie. Si vous tapez correctement un mot en bleu, vous en obtiendrez cinq.
    Il y a un compteur qui vous permet de passer au niveau suivant lorsque vous avez tapé 100 mots corrects ou un multiple de 100.
    À chaque niveau, les mots disparaîtront plus rapidement.
    La vitesse est calculée en fonction de votre niveau et du nombre de mots corrects tapés, en utilisant également une fonction décroissante.
    Les mots s'estompent en fonction de la vitesse. Au début, ils peuvent prendre jusqu'à 10 secondes, mais ensuite ils changent toutes les secondes. N'oubliez pas de surveiller le minuteur.
    Il y a beaucoup de texte affiché dans le jeu: précision, vies, secondes, vitesse.
    À la fin du jeu, l'application enregistrera toutes ces informations dans des fichiers txt, avec votre nom.

Voici quelques fonctionnalités de l'application:

    Pour maintenir le jeu en cours d'exécution, nous utilisons une fonction "Run" dans un planificateur qui s'exécute toutes les secondes.
    Pour améliorer les performances du jeu et éviter tout ralentissement, nous avons utilisé plusieurs threads: un pour mettre à jour le score, un autre pour mettre à jour les vies, etc.
    Pour ajuster le temps, nous avons utilisé la bibliothèque Timer.
    Pour calculer la vitesse, nous avons effectué le calcul dans la fonction "Run" afin qu'elle s'exécute en même temps que tous les threads.
    Pour tester l'application, un fichier txt avec des mots complexes est déjà inclus
