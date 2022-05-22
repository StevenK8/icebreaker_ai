package games.icebreaker;

import java.awt.*;

public class Case {

    private String value;
    private int scoreCase = 0;
    private int scoreVoisin = 0;
    private boolean isVisited = false;
    private Point position;

    public Case(String pvalue, Point p){
        value = pvalue;
        position = p;
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

    public Case copy() {
        return new Case(value, position);
    }

}
