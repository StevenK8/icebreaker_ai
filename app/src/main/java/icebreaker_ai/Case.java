package icebreaker_ai;

import java.awt.*;

public class Case {

    private String value;
    private int scoreCase;
    private int scoreVoisin;
    private boolean isVisited = false;
    private Point position;

    public Case(String pvalue, Point p, int pscore, int pscoreVoisin){
        value = pvalue;
        scoreCase = pscore;
        position = p;
        scoreVoisin = 0;
    }

    public String getValue(){
        return value;
    }
    public void setValue(String newValue){
        value = newValue;
    }

    public int getScore(){
        return scoreCase;
    }
    public void setScore(int newScore){
        scoreCase = newScore;
    }

    public boolean isVisited(){
        return isVisited;
    }
    public void setIsVisited(boolean visit){
        isVisited = visit;
    }

    public Point getPoint(){
        return position;
    }
    
    public void setPoint(Point p){
        position = p;
    }

    public int getScoreVoisin(){
        return scoreVoisin;
    }
    public void setScoreVoisin(int score){
        scoreVoisin = score;
    }

}
