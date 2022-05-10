package icebreaker_ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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
        try (// read file and set board
        BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int l = 1;
            int pos=0;
            String line = br.readLine();
            while (line != null) {
                if (l==1){
                    redScore = Integer.parseInt(line.split(" ")[3]);
                    blackScore = Integer.parseInt(line.split(" ")[8]);
                }else if (l>=3){
                    pos = Integer.parseInt(line.split(" ")[0])-65;
                    board.set(pos,new ArrayList<String>(Arrays.asList(line.split(" "))));
                    board.get(pos).remove(0);
                    
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> possibleMoves(String role) {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
