package app.src.main.java.icebreaker_ai;

public class Board {
    public static void main(String[] args) {
        MyChallenger challenger = new MyChallenger();
        challenger.setBoardFromFile("board1.txt");
        challenger.boardToString();
    }
}
