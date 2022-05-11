package icebreaker_ai;

public class Case {

    private String value;
    private int scoreCase;
    private boolean isVisited = false;

    public Case(String pvalue, int pscore){
        value = pvalue;
        scoreCase = pscore;
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
}
