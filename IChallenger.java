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

    String tie();

    String boardToString();

    void setBoardFromFile(String filename);

    Set<String> possibleMoves(String role);
}