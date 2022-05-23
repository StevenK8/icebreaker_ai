# Devoir 2022

Structure de départ pour le devoir sur le jeu *Brise-glace*.

Si vous le souhaitez, vous pouvez changer l'organisation ou supprimer les fichiers présents initialement dans ce dépôt.
Les seuls fichiers que vous devez impérativement conserver sont les suivants :

- le fichier `.gitignore` dans la racine du projet
- l'interface `IChallenger.java`
- la classe `MyChallenger.java`
- la classe `Client.java` (utile pour la partie 3)
- la classe `Message.java` (utile pour la partie 3)

## Partie 2

### Consignes

1. implémenter dans `MyChallenger` toutes les fonctions de l'interface `IChallenger`, à l'exception de la fonction `bestMove`, qui ne sera à écrire que pour la troisième partie;

2. écrire une courte description (un ou deux paragraphes, ci-dessous) de l'algorithme utilisé pour `possibleMoves`.

### Commandes pour lancer les joueurs

java -cp IB_1_o.jar games.icebreaker.IBDuel -p 4536 -g

java -jar .\ia.jar -p 4536 -s localhost -c games.icebreaker.MyChallenger

java -cp IB_1_o.jar iialib.games.contest.Client -p 4536 -s localhost -c games.icebreaker.IBAlphaBetaChallenger
