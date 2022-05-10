package icebreaker_ai;
import java.util.Set;

public interface IChallenger{
// −−−−−−−−−−−−−−−−−−−−−− Fonctions pour le tournoi −−−−−−−−−−−−−−−−−−−−−

    /*
    * L ’ arbitre vous demande le nom de votre équipe.
    * @return le nom de votre équipe sous la forme "Nom1 − Nom2"
    */
    String teamName();

    /**
    * L ’ arbitre vous signale votre rôle au début de la partie.
    * Vous pouvez préparer votre représentation interne du plateau à ce moment.
    * @param role le rôle qui vous est assigné ( "RED" ou "BLACK" )
    */
    void setRole(String role);

    void iPlay(String move);

    void otherPlay(String move);

    String bestMove();

    String victory();

    String defeat();

    /**
    * L’arbitre vous signale que la parties s’est soldée par une égalité.
    * @return un petit message ou une bannière de partie nulle.
    */
    String tie();


    // −−−−−−−−−−−−−−−−−−−−−− Fonctions pour les tests −−−−−−−−−−−−−−−−−−−−−

    /**
    * Vous devez renvoyer une chaîne de caractères décrivant l’état du plateau.
    * return la chaîne représentant le plateau 
    */
    String boardToString();

    /**
    * Vous devez mettre à jour votre représentation interne selon l’état du plateau décrit dans un fichier texte.
    * @param fileName le nom du fichier à lire
    */
    void setBoardFromFile(String filename);

    /**
    * Vous devez renvoyer l’ensemble des coups possibles pour l’un des joueurs (d’après l’état actuel du
    plateau dans votre représentation interne).
    * @param role le rôle du joueur dont il faut renvoyer les coups ( "RED" ou "BLACK" )
    * @return l’ ensemble de coups possibles pour le joueur désigé ( sous la forme "D2−C2 " )
    */
    Set<String> possibleMoves(String role);
}

