import java.util.Set;

public interface IChallenger{
    String teamName();

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