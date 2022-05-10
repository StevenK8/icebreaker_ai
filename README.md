# icebreaker_ai
L’objectif de ce projet est de modéliser et réaliser un joueur artificiel capable
de jouer à un jeu à deux joueurs. 

Ce projet aborde le jeu Brise-glace (Icebreaker), conc¸u par Mark Steere (https://www.marksteeregames.com/).

# Présentation du jeu

## a) Plateau et pièces
Ce jeu se joue sur un plateau hexagonal de 61 cases (5 cases de côté). Une case peut être soit vide,
soit occupée par un navire, soit occupée un iceberg. Chaque joueur dispose de 3 navires, représentés
par des pièces identiques, de couleur rouge ou noire. Les icebergs sont représentés par des pions blancs.
Dans la suite, nous conviendrons de référencer chaque case du plateau par une chaine de caractère
formée d’une lettre suivie d’un chiffre. Les lettres de A à I référencent les lignes du plateau (de haut
en bas) alors que les chiffres de 1 à 9 référencent les positions à l’intérieur des lignes (de gauche à
droite).
En debut de partie, nous supposerons que les pièces sont disposées sur le plateau suivant la configuration
décrite sur la figure 1. La figure 2 explicite la convention pour référencer les cases.

## b) Déplacements autorisés
À chaque tour de jeu, le joueur ayant le trait doit déplacer un de ses navires. Un navire ne peut
être déplacé que sur une case adjacente, vide ou occupée par un iceberg (deux navires ne peuvent
pas occcuper la même case). De plus, un navire ne peut être déplacé que de manière à réduire sa
distance à l’iceberg le plus proche. La distance entre un navire et un iceberg est mesurée par
la longueur du plus court chemin qui les relie, en contournant les autres navires.
Si plusieurs cases adjacentes permettent de réduire la distance au plus proche iceberg, elles peuvent
toutes faire l’objet d’un déplacement valide. Si un navire se trouve à égale distance de plusieurs icebergs,
il est possible de le diriger vers n’importe lequel d’entre eux. Si un navire est adjacent à un ou plusieurs
icebergs (distance 1), il ne peut être déplacé que sur l’un d’entre eux. Un navire prenant la place d’un
iceberg le capture : celui-ci est retiré du plateau, et le joueur le récupère.

## c) Déroulement d’une partie
Un partie se déroule de la façon suivante :
• Le joueur rouge est le premier à jouer.
• Les tours des joueurs alternent pour la suite de la partie.
• Un tour consiste en un déplacement de pièce, suivant les règles indiquées ci-dessus.
• Un joueur est obligé de déplacer une de ses pièces, il ne peut passer son tour.
• Dès qu’un joueur a capturé 28 icebergs, il gagne et la partie prend fin.
