package icebreaker_ai;

public class Board {
    public static void main(String[] args) {
        MyChallenger challenger = new MyChallenger();
        challenger.setBoardFromFile("Exple_plateau_1.txt");
        System.out.println(challenger.boardToString());
        System.out.println(challenger.defeat());
        challenger.iPlay("D2-C2");
    }
}
