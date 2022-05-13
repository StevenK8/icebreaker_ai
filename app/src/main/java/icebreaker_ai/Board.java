package icebreaker_ai;

public class Board {
    public static void main(String[] args) {
        MyChallenger challenger = new MyChallenger();
        challenger.setBoardFromFile("Plateau_initial.txt");

        challenger.setRole("RED");
        while(!challenger.isOver()){
            challenger.iPlay(challenger.possibleMoves(challenger.getRole()).iterator().next());
            System.out.println(challenger.boardToString());
            if(!challenger.isOver()){
                challenger.otherPlay(challenger.possibleMoves(challenger.getRoleAdversaire()).iterator().next());

            }
        }
    }
}
