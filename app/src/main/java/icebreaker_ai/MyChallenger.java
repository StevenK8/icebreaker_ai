package icebreaker_ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
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

    private String getLigneToString(int ligne){
        String res = "";
        for(String s : board.get(ligne)){
            if(s.equals("\u2022")){
                res += "•" + " ";
            }
            else{
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
                        if (line.contains("R") || line.contains("B")) {
                            int i = 0;
                            for (String s : line.trim().split("\\s+")) {
                                i++;
                                if (s.contains("R")) {
                                    redPoints.add(new Point(l - 3, i));
                                } else if (s.contains("B")) {
                                    blackPoints.add(new Point(l - 3, i));
                                }
                            }
                        }
                    }
                    l++;
                }
            } catch (Exception e) {
                System.out.println("Unreadable file Error : "+e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> possibleMoves(String role) {
        // TODO Auto-generated method stub
        return null;
    }

}
