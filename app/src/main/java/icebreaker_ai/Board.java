package icebreaker_ai;

public class Board {
    public static void main(String[] args) {
        MyChallenger challenger = new MyChallenger();
        challenger.setBoardFromFile("Plateau_initial.txt");

        challenger.setRole("BLACK");
        while(!challenger.isOver()){
            challenger.iPlay(challenger.possibleMoves("RED").iterator().next());
            System.out.println(challenger.boardToString());
            challenger.otherPlay(challenger.possibleMoves("BLACK").iterator().next());
        }
    }
}
