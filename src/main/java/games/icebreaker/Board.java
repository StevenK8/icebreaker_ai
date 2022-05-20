package games.icebreaker;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Case>> board = new ArrayList<ArrayList<Case>>();

    public Board(){
        ArrayList<Case> array1 = new ArrayList<>();
        ArrayList<Case> array2 = new ArrayList<>();
        ArrayList<Case> array3 = new ArrayList<>();
        ArrayList<Case> array4 = new ArrayList<>();
        ArrayList<Case> array5 = new ArrayList<>();
        ArrayList<Case> array6 = new ArrayList<>();
        ArrayList<Case> array7 = new ArrayList<>();
        ArrayList<Case> array8 = new ArrayList<>();
        ArrayList<Case> array9 = new ArrayList<>();

        array1.add(0,new Case("R", new Point(0,0)));
        array1.add(1,new Case("o", new Point(0,1)));
        array1.add(2,new Case("o", new Point(0,2)));
        array1.add(3,new Case("o", new Point(0,3)));
        array1.add(4,new Case("B", new Point(0,4)));

        array2.add(0, new Case("o", new Point(1,0)));
        array2.add(1, new Case("o", new Point(1,1)));
        array2.add(2, new Case("o", new Point(1,2)));
        array2.add(3, new Case("o", new Point(1,3)));
        array2.add(4, new Case("o", new Point(1,4)));
        array2.add(5, new Case("o", new Point(1,5)));

        array3.add(0, new Case("o", new Point(2,0)));
        array3.add(1, new Case("o", new Point(2,1)));
        array3.add(2, new Case("o", new Point(2,2)));
        array3.add(3, new Case("o", new Point(2,3)));
        array3.add(4, new Case("o", new Point(2,4)));
        array3.add(5, new Case("o", new Point(2,5)));
        array3.add(6, new Case("o", new Point(2,6)));

        array4.add(0, new Case("o", new Point(3,0)));
        array4.add(1, new Case("o", new Point(3,1)));
        array4.add(2, new Case("o", new Point(3,2)));
        array4.add(3, new Case("o", new Point(3,3)));
        array4.add(4, new Case("o", new Point(3,4)));
        array4.add(5, new Case("o", new Point(3,5)));
        array4.add(6, new Case("o", new Point(3,6)));
        array4.add(7, new Case("o", new Point(3,7)));

        array5.add(0, new Case("R", new Point(4,0)));
        array5.add(1, new Case("o", new Point(4,1)));
        array5.add(2, new Case("o", new Point(4,2)));
        array5.add(3, new Case("o", new Point(4,3)));
        array5.add(4, new Case("o", new Point(4,4)));
        array5.add(5, new Case("o", new Point(4,5)));
        array5.add(6, new Case("o", new Point(4,6)));
        array5.add(7, new Case("o", new Point(4,7)));
        array5.add(8, new Case("B", new Point(4,8)));

        array6.add(0, new Case("o", new Point(5,0)));
        array6.add(1, new Case("o", new Point(5,1)));
        array6.add(2, new Case("o", new Point(5,2)));
        array6.add(3, new Case("o", new Point(5,3)));
        array6.add(4, new Case("o", new Point(5,4)));
        array6.add(5, new Case("o", new Point(5,5)));
        array6.add(6, new Case("o", new Point(5,6)));
        array6.add(7, new Case("o", new Point(5,7)));

        array7.add(0, new Case("o", new Point(6,0)));
        array7.add(1, new Case("o", new Point(6,1)));
        array7.add(2, new Case("o", new Point(6,2)));
        array7.add(3, new Case("o", new Point(6,3)));
        array7.add(4, new Case("o", new Point(6,4)));
        array7.add(5, new Case("o", new Point(6,5)));
        array7.add(6, new Case("o", new Point(6,6)));

        array8.add(0, new Case("o", new Point(7,0)));
        array8.add(1, new Case("o", new Point(7,1)));
        array8.add(2, new Case("o", new Point(7,2)));
        array8.add(3, new Case("o", new Point(7,3)));
        array8.add(4, new Case("o", new Point(7,4)));
        array8.add(5, new Case("o", new Point(7,5)));

        array9.add(0,new Case("R", new Point(8,0)));
        array9.add(1,new Case("o", new Point(8,1)));
        array9.add(2,new Case("o", new Point(8,2)));
        array9.add(3,new Case("o", new Point(8,3)));
        array9.add(4,new Case("B", new Point(8,4)));

        board.add(0, array1);
        board.add(1, array2);
        board.add(2, array3);
        board.add(3, array4);
        board.add(4, array5);
        board.add(5, array6);
        board.add(6, array7);
        board.add(7, array8);
        board.add(8, array9);
    }

    public ArrayList<ArrayList<Case>> getBoard(){
        return board;
    }
}
