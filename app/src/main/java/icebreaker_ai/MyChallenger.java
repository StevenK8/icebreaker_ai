package icebreaker_ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MyChallenger implements IChallenger {
    private final static int MAXSCORE = 28;
    private int redScore = 0;
    private int blackScore = 0;
    private ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();

    private ArrayList<Point> redPoints = new ArrayList<Point>();
    private ArrayList<Point> blackPoints = new ArrayList<Point>();

    private String role;

    public MyChallenger() {
        role = "";
    }

    @Override
    public String teamName() {
        return "Steven Kerautret - Damien Chancerel";
    }

    @Override
    public void setRole(String role) {
        this.role = role;
        
    }

    private int convertLetterToIndex(char c) {
        int index = (c - 65);
        return index;
    }

    private String convertIndexToLetter(int i) {
        String letter = "";
        letter += (char) (i + 65);
        return letter;
    }

    @Override
    public void iPlay(String move) {
        ArrayList<String> deplacement = new ArrayList<String>(Arrays.asList(move.split("-")));
        int ligneOrigine  = convertLetterToIndex(deplacement.get(0).toCharArray()[0]);
        int colonneOrigine = Integer.parseInt(deplacement.get(0).toCharArray()[1] + "") - 1;

        int ligneDestination  = convertLetterToIndex(deplacement.get(1).toCharArray()[0]);
        int colonneDestination = Integer.parseInt(deplacement.get(1).toCharArray()[1] + "") - 1;

        String joueurActuel = role.equals("RED") ? "R" : "B";
        //reset l'ancienne position du bateau à .
        board.get(ligneOrigine).set(colonneOrigine, ".");

        //on se déplace sur un iceberg --> on augmente le score du bon joueur
        if(board.get(ligneDestination).get(colonneDestination).equals(".")
            && joueurActuel.equals("R")){
            redScore++;
        }
        else if(board.get(ligneDestination).get(colonneDestination).equals(".")
                && joueurActuel.equals("B")){
            blackScore++;
        }
        //set la nouvelle position à R ou B
        board.get(ligneDestination).set(colonneDestination, joueurActuel);
    }

    @Override
    public void otherPlay(String move) {
        ArrayList<String> deplacement = new ArrayList<String>(Arrays.asList(move.split("-")));
        int ligneOrigine  = convertLetterToIndex(deplacement.get(0).toCharArray()[0]);
        int colonneOrigine = Integer.parseInt(deplacement.get(0).toCharArray()[1] + "") - 1;

        int ligneDestination  = convertLetterToIndex(deplacement.get(1).toCharArray()[0]);
        int colonneDestination = Integer.parseInt(deplacement.get(1).toCharArray()[1] + "") - 1;

        String joueurActuel = board.get(ligneOrigine).get(colonneOrigine).equals("R") ? "R" : "B";
        //reset l'ancienne position du bateau à .
        board.get(ligneOrigine).set(colonneOrigine, ".");

        //on se déplace sur un iceberg --> on augmente le score du bon joueur
        if(board.get(ligneDestination).get(colonneDestination).equals(".")
                && joueurActuel.equals("R")){
            redScore++;
        }
        else if(board.get(ligneDestination).get(colonneDestination).equals(".")
                && joueurActuel.equals("B")){
            blackScore++;
        }
        //set la nouvelle position à R ou B
        board.get(ligneDestination).set(colonneDestination, joueurActuel);
        
    }

    @Override
    public String bestMove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String victory() {
        String res = redScore==MAXSCORE ? "RED" : blackScore==MAXSCORE ? "BLACK" : "";

        if(res != ""){
            res += " WINS !";
        }

        return res;
    }

    @Override
    public String defeat() {
        String res = redScore==MAXSCORE ? "BLACK" : blackScore==MAXSCORE ? "RED" : "";

        if(res != ""){
            res += " LOSE !";
        }

        return res;
    }

    @Override
    public String tie() {
        return "égalité";
    }

    private String getLigneToString(int ligne) {
        String res = "";
        for (String s : board.get(ligne)) {
            if (s.equals("\u2022")) {
                // res += "•" + " ";
                // TODO : restauter le caractère "•"
                res += "." + " ";
            } else {
                res += s + " ";
            }

        }
        return res;
    }

    @Override
    public String boardToString() {
        String res = "Red Score : " + redScore + " --- Black Score : " + blackScore + "\n\n";
        res += "A      " + getLigneToString(0) + "     \n" +
                "B     " + getLigneToString(1) + "    \n" +
                "C    " + getLigneToString(2) + "   \n" +
                "D   " + getLigneToString(3) + "  \n" +
                "E  " + getLigneToString(4) + " \n" +
                "F   " + getLigneToString(5) + "  \n" +
                "G    " + getLigneToString(6) + "   \n" +
                "H     " + getLigneToString(7) + "    \n" +
                "I      " + getLigneToString(8) + "     ";

        return res;
    }

    @Override
    public void setBoardFromFile(String filename) {
        try (
                InputStream ioStream = this.getClass()
                        .getClassLoader()
                        .getResourceAsStream(filename);
                InputStreamReader streamReader = new InputStreamReader(ioStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader);) {
            int l = 1;
            try {
                for (String line; (line = reader.readLine()) != null;) {
                    if (l == 1) {
                        redScore = Integer.parseInt(line.trim().split("\\s+")[3]);
                        blackScore = Integer.parseInt(line.trim().split("\\s+")[8]);
                    } else if (l >= 3) {
                        ArrayList<String> a = new ArrayList<String>(Arrays.asList(line.trim().split("\\s+")));
                        // System.out.println(a);
                        a.remove(0);
                        board.add(a);
                        if (a.contains("R") || a.contains("B")) {
                            int i = 0;
                            for (String s : a) {
                                if (s.contains("R")) {
                                    redPoints.add(new Point(l - 3, i));
                                } else if (s.contains("B")) {
                                    blackPoints.add(new Point(l - 3, i));
                                }
                                i++;
                            }
                        }
                    }
                    l++;
                }
            } catch (Exception e) {
                System.out.println("Unreadable file Error : " + e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValid(int x, int y){
        try{
            if(!board.get(x).isEmpty() || !board.get(x).get(y).isEmpty()){
                if(x >= 0 && x < board.size() && y >= 0 && y < board.get(x).size()){
                    if(board.get(x).get(y).equals("\u2022") || board.get(x).get(y).equals("o")){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        return false;
    }

    private Set<String> getPossibleMoves(ArrayList<Point> points) {
        Set<String> res = new java.util.HashSet<String>();
        for (Point p : points) {
            //Check cases a coté
            if(isValid(p.x, p.y-1)){
                res.add(convertIndexToLetter(p.x)+ (p.y +1) +"-"+ convertIndexToLetter(p.x)+(p.y));
            }
            if(isValid(p.x, p.y + 1)){
                res.add(convertIndexToLetter(p.x)+ (p.y +1) +"-"+ convertIndexToLetter(p.x)+(p.y + 2));
            }

            // Check ligne au dessus plus grande
            if(board.get((p.x) - 1).size() > board.get(p.x).size()){
                if(isValid(p.x - 1,  p.y)){
                    res.add(convertIndexToLetter(p.x)+ (p.y +1) +"-"+ convertIndexToLetter((p.x)-1)+(p.y + 1));
                }
                if(isValid(p.x - 1,  p.y + 1)){
                    res.add(convertIndexToLetter(p.x)+ (p.y +1) +"-"+ convertIndexToLetter((p.x)-1)+(p.y + 2));
                }
            }
            // Check ligne au dessus plus petite
            else if(board.get((p.x) - 1).size() < board.get(p.x).size()){
                if(isValid(p.x - 1,  p.y)){
                    res.add(convertIndexToLetter(p.x)+ (p.y +1) +"-"+ convertIndexToLetter((p.x)-1)+(p.y+1));
                }
                if(isValid(p.x - 1,  p.y-1)){
                    res.add(convertIndexToLetter(p.x)+ (p.y +1) +"-"+ convertIndexToLetter((p.x)-1)+(p.y));
                }
            }

            //check ligne en dessous plus grande
            if(board.get((p.x) + 1).size() > board.get(p.x).size()){
                if(isValid(p.x + 1,  p.y)){
                    res.add(convertIndexToLetter(p.x)+ (p.y +1) +"-"+ convertIndexToLetter((p.x)+1)+(p.y + 1));
                }
                if(isValid(p.x + 1,  p.y + 1)) {
                    res.add(convertIndexToLetter(p.x) + (p.y + 1) + "-" + convertIndexToLetter((p.x) + 1) + (p.y + 2));
                }
            }
            //check ligne en dessous plus petite
            else if(board.get((p.x) + 1).size() < board.get(p.x).size()){
                if(isValid(p.x + 1,  p.y)) {
                    res.add(convertIndexToLetter(p.x)+ (p.y +1) +"-"+ convertIndexToLetter((p.x)+1)+(p.y + 1));
                }
                if(isValid(p.x + 1,  p.y-1)) {
                    res.add(convertIndexToLetter(p.x)+ (p.y +1) +"-"+ convertIndexToLetter((p.x)+1)+(p.y));
                }
              }


        }

        return res;
    }

    @Override
    public Set<String> possibleMoves(String role) {
        if (role.equals("RED")) {
            return getPossibleMoves(redPoints);
        } else if (role.equals("BLACK")) {
            return getPossibleMoves(blackPoints);
        } else {
            return null;
        }
    }

}
