package icebreaker_ai;

import java.util.ArrayList;
import java.util.Set;
import java.awt.Point;


public class MyChallenger implements IChallenger {
    private final static int MAXSCORE = 28;
    private int redScore = 0;
    private int blackScore = 0;
    private ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();

    private ArrayList<Point> redPoints = new ArrayList<Point>();
    private ArrayList<Point> blackPoints = new ArrayList<Point>();


    public MyChallenger() {
        // TODO Auto-generated method stub
    }

    @Override
    public String teamName() {
        return "Steven Kerautret - Damien Chancerel";
    }

    @Override
    public void setRole(String role) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void iPlay(String move) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void otherPlay(String move) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String bestMove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String victory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String defeat() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String tie() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String boardToString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBoardFromFile(String filename) {
        // read file and set board     
    }

    @Override
    public Set<String> possibleMoves(String role) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
