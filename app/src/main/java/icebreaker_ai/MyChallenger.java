package icebreaker_ai;

// import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    private ArrayList<ArrayList<Case>> board = new ArrayList<ArrayList<Case>>();

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
        int ligneOrigine = convertLetterToIndex(deplacement.get(0).toCharArray()[0]);
        int colonneOrigine = Integer.parseInt(deplacement.get(0).toCharArray()[1] + "") - 1;

        int ligneDestination = convertLetterToIndex(deplacement.get(1).toCharArray()[0]);
        int colonneDestination = Integer.parseInt(deplacement.get(1).toCharArray()[1] + "") - 1;

        String joueurActuel = role.equals("RED") ? "R" : "B";
        // reset l'ancienne position du bateau à .
        board.get(ligneOrigine).get(colonneOrigine).setValue(".");

        // on se déplace sur un iceberg --> on augmente le score du bon joueur
        if (board.get(ligneDestination).get(colonneDestination).getValue().equals(".")
                && joueurActuel.equals("R")) {
            redScore++;
        } else if (board.get(ligneDestination).get(colonneDestination).getValue().equals(".")
                && joueurActuel.equals("B")) {
            blackScore++;
        }
        // set la nouvelle position à R ou B
        board.get(ligneDestination).get(colonneDestination).setValue(joueurActuel);
    }

    @Override
    public void otherPlay(String move) {
        ArrayList<String> deplacement = new ArrayList<String>(Arrays.asList(move.split("-")));
        int ligneOrigine = convertLetterToIndex(deplacement.get(0).toCharArray()[0]);
        int colonneOrigine = Integer.parseInt(deplacement.get(0).toCharArray()[1] + "") - 1;

        int ligneDestination = convertLetterToIndex(deplacement.get(1).toCharArray()[0]);
        int colonneDestination = Integer.parseInt(deplacement.get(1).toCharArray()[1] + "") - 1;

        String joueurActuel = board.get(ligneOrigine).get(colonneOrigine).equals("R") ? "R" : "B";
        // reset l'ancienne position du bateau à .
        board.get(ligneOrigine).get(colonneOrigine).setValue(".");

        // on se déplace sur un iceberg --> on augmente le score du bon joueur
        if (board.get(ligneDestination).get(colonneDestination).equals(".")
                && joueurActuel.equals("R")) {
            redScore++;
        } else if (board.get(ligneDestination).get(colonneDestination).equals(".")
                && joueurActuel.equals("B")) {
            blackScore++;
        }
        // set la nouvelle position à R ou B
        board.get(ligneDestination).get(colonneDestination).setValue(joueurActuel);

    }

    @Override
    public String bestMove() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String victory() {
        String res = redScore == MAXSCORE ? "RED" : blackScore == MAXSCORE ? "BLACK" : "";

        if (res != "") {
            res += " WINS !";
        }

        return res;
    }

    @Override
    public String defeat() {
        String res = redScore == MAXSCORE ? "BLACK" : blackScore == MAXSCORE ? "RED" : "";

        if (res != "") {
            res += " LOSE !";
        }

        return res;
    }

    private String coordinatesToText(int x, int y) {
        return convertIndexToLetter(x) + (y + 1);
    }

    private Point textToPoint(String text) {
        if (text.length() == 2) {
            return new Point(convertLetterToIndex(text.toCharArray()[0]),
                    Integer.parseInt(text.toCharArray()[1] + "") - 1);
        } else {
            return new Point(convertLetterToIndex(text.toCharArray()[3]),
                    Integer.parseInt(text.toCharArray()[4] + "") - 1);
        }
    }

    @Override
    public String tie() {
        return "égalité";
    }

    private String getLigneToString(int ligne) {
        String res = "";
        for (Case c : board.get(ligne)) {
            if (c.getValue().equals("\u2022")) {
                // res += "•" + " ";
                // TODO : restauter le caractère "•"
                res += ".   ";
            } else {
                res += c.getValue() + "   ";
            }

        }
        return res;
    }

    @Override
    public String boardToString() {
        String res = "Red Score : " + redScore + " --- Black Score : " + blackScore + "\n\n";
        res += "A           " + getLigneToString(0) + "     \n" +
                "B        " + getLigneToString(1) + "    \n" +
                "C      " + getLigneToString(2) + "   \n" +
                "D    " + getLigneToString(3) + "  \n" +
                "E  " + getLigneToString(4) + " \n" +
                "F    " + getLigneToString(5) + "  \n" +
                "G      " + getLigneToString(6) + "   \n" +
                "H        " + getLigneToString(7) + "    \n" +
                "I          " + getLigneToString(8) + "     ";

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
                        ArrayList<Case> ligneCase = new ArrayList<>();
                        for (int i = 0; i < a.size(); i++) {
                            Case c = new Case(a.get(i), new Point(l - 3, i), 0, 0);
                            ligneCase.add(c);
                        }
                        board.add(ligneCase);
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
            System.out.println("IOException Error : " + e);
            e.printStackTrace();
        }
    }

    private boolean isValid(int x, int y) {

        if ((x >= 0 && x < board.size() && y >= 0 && y < board.get(x).size())) {
            if (!board.isEmpty()
                    && !board.get(x).isEmpty()
                    && !board.get(x).get(y).getValue().isEmpty()) {
                if (board.get(x).get(y).getValue().equals("\u2022") || board.get(x).get(y).getValue().equals("o")) {
                    return true;
                }
            }
        }
        return false;
    }

    // Set<String> res = new java.util.HashSet<String>();
    // Set<String> resIfIceberg = new java.util.HashSet<String>();
    // ArrayList<Point> possiblePoints = new ArrayList<>();

    private void addIfValid(Point p, Point p2, Set<String> res, Set<String> resIfIceberg,
            ArrayList<Point> possiblePoints) {
        if (isValid(p2.x, p2.y)) {
            possiblePoints.add(new Point(p2.x, p2.y));
            //board.get(p2.x).get(p2.y).setIsVisited(true);
            if (board.get(p2.x).get(p2.y).getValue().equals("o")) {
                resIfIceberg.add(coordinatesToText(p.x, p.y) + "-" + coordinatesToText(p2.x, p2.y));
            }
            res.add(coordinatesToText(p.x, p.y) + "-" + coordinatesToText(p2.x, p2.y));
        }
    }

    boolean isIceberg = false;

    private Case getCaseFromPoint(Point p){
        return board.get(p.x).get(p.y);
    }

    private Point getPointFromCase(Case c){
        return new Point(c.getPoint().x, c.getPoint().y);
    }

    private void clearVisitedAndScore(){
        for(int i = 0; i < board.size(); i++){
            for(int j = 0; j < board.get(i).size(); j++){
                board.get(i).get(j).setScore(0);
                board.get(i).get(j).setScoreVoisin(0);
                board.get(i).get(j).setIsVisited(false);
            }
        }
    }

    private ArrayList<Point> bfs(Point start){
        LinkedList<Point> queue = new LinkedList<Point>();
        ArrayList<Boolean> visited = new ArrayList<>();
        ArrayList<Point> res = new ArrayList<>();
        queue.add(start);
        Case cStart = getCaseFromPoint(start);
        cStart.setScore(0);

        while(queue.size() != 0){
            start = queue.poll();
            if(getCaseFromPoint(start).getValue().equals("o")){
                res.add(start);
            }

            ArrayList<Point> adj = stringsToPoints(getPossibleMoves(start));
            for(Point p : adj){
                Case c = getCaseFromPoint(p);
                if(!c.isVisited()){
                    c.setIsVisited(true);
                    c.setScore(getCaseFromPoint(start).getScore() + 1);
                    queue.add(p);
                }
            }
        }
        clearVisitedAndScore();
        ArrayList<Point> positionIcebergProche = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(Point p : res){
            if(getCaseFromPoint(p).getScore() < min){
                positionIcebergProche.clear();
                positionIcebergProche.add(p);
                min = getCaseFromPoint(p).getScore();
            }
            else if(getCaseFromPoint(p).getScore() == min){
                positionIcebergProche.add(p);
            }
        }
        return positionIcebergProche;
    }

    private Set<Point> iceberg_breadth_search(Point start, Queue<Point> possiblePoints, Set<Point> visited) {
        // Set<String> list = new HashSet<>();
        Queue<Point> queue = new LinkedList<Point>(possiblePoints);
        // ArrayList<Point> points = new ArrayList<>();
        visited.add(start);
        Point p;

        if (queue.isEmpty()) {
            return visited;
        }
        System.out.println("bfs : " + start);
        while (!queue.isEmpty()) {
            p = queue.poll();
            if (!visited.contains(p)) {
                visited.add(p);
                queue.addAll(stringsToPoints(getPossibleMoves(p)));
                if (isIceberg) { // iceberg found
                    System.out.println("Iceberg found next to " + p.x + "," + p.y + " (" + coordinatesToText(p.x, p.y) + ")");
                    // return visited;
                    // for (Point around : points) {
                    // board.get(around.x).get(around.y).setScore(0); // 0: iceberg
                    // }
                    // int i=1;
                    // for (Point path : visited) {
                    // board.get(path.x).get(path.y).setScore(i);
                    // i++;
                    // }
                } else {
                    // for (Point around : points){
                    // board.get(around.x).get(around.y).setScore(-2); // -2: no iceberg
                    // }
                    // Point first = iceberg_breadth_search(points, visited).iterator().next();
                    // visited.add(first);
                    // return iceberg_breadth_search(p,queue, visited);
                    // possiblePoints.addAll(points);
                }
            }
        }
        return visited;
    }

    // convert Set<String> to ArrayList<Point>
    private ArrayList<Point> stringsToPoints(Set<String> list) {
        ArrayList<Point> res = new ArrayList<>();
        for (String s : list) {
            res.add(textToPoint(s));
        }
        return res;
    }

    private Set<String> getPossibleMoves(Point p) {
        Set<String> res = new java.util.HashSet<String>();
        Set<String> resIfIceberg = new java.util.HashSet<String>();
        ArrayList<Point> possiblePoints = new ArrayList<>();
        // Check cases a coté

        addIfValid(p, new Point(p.x, p.y - 1), res, resIfIceberg, possiblePoints);

        addIfValid(p, new Point(p.x, p.y + 1), res, resIfIceberg, possiblePoints);

        // Check ligne au dessus plus grande
        if (p.x > 0 && board.get((p.x) - 1).size() > board.get(p.x).size()) {
            addIfValid(p, new Point(p.x - 1, p.y), res, resIfIceberg, possiblePoints);
            addIfValid(p, new Point(p.x - 1, p.y + 1), res, resIfIceberg, possiblePoints);
        }
        // Check ligne au dessus plus petite
        else if (p.x > 0 && board.get((p.x) - 1).size() < board.get(p.x).size()) {
            addIfValid(p, new Point(p.x - 1, p.y), res, resIfIceberg, possiblePoints);
            addIfValid(p, new Point(p.x - 1, p.y - 1), res, resIfIceberg, possiblePoints);
        }
        // check ligne en dessous plus grande
        if (p.x < board.size() && p.y < board.get(p.x).size() && board.get((p.x) + 1).size() > board.get(p.x).size()) {
            addIfValid(p, new Point(p.x + 1, p.y), res, resIfIceberg, possiblePoints);
            addIfValid(p, new Point(p.x + 1, p.y + 1), res, resIfIceberg, possiblePoints);
        }
        // check ligne en dessous plus petite
        else if (p.x < board.size() && board.get((p.x) + 1).size() < board.get(p.x).size()) {
            addIfValid(p, new Point(p.x + 1, p.y), res, resIfIceberg, possiblePoints);
            addIfValid(p, new Point(p.x + 1, p.y - 1), res, resIfIceberg, possiblePoints);
        }

        // Check si un iceberg est à coté --> on enleve les cases vides des possibles
        // moves
        if (resIfIceberg.isEmpty()) {
            isIceberg = false;
            return res;
        } else {
            isIceberg = true;
            return resIfIceberg;
        }
    }

    private Set<String> getPossibleMoves(ArrayList<Point> points) {
        Set<String> res = new java.util.HashSet<String>();
        // res.clear();
        // resIfIceberg.clear();
        // possiblePoints.clear();

        for (Point p : points) {
            res.addAll(getPossibleMoves(p));
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

    public void possibleMovesSearch(String role) {
        Set<String> listPossibleMove = new HashSet<>();
        for (Point p : blackPoints) {
            ArrayList<Point> voisins = new ArrayList<Point>(stringsToPoints(getPossibleMoves(p)));
            ArrayList<Point> listIceberg = bfs(p);

            listPossibleMove.addAll(bfsVoisin(p, voisins, listIceberg));
        }
        for(String s : listPossibleMove)
        System.out.println(s);
    }

    private Set<String> bfsVoisin(Point source, ArrayList<Point> voisins, ArrayList<Point> icebergs){
        LinkedList<Point> queue = new LinkedList<Point>();
        ArrayList<Boolean> visited = new ArrayList<>();
        ArrayList<Point> res = new ArrayList<Point>();

        Set<String> resultat = new HashSet<>();
        int min = Integer.MAX_VALUE;

        for(Point voisin : voisins){
            Point voisinOriginal = voisin;
            queue.add(voisin);
            Case cStart = getCaseFromPoint(voisin);
            cStart.setScore(0);
            while(queue.size() != 0){
                Point p = queue.poll();
                //todo : change "B" --> getRole
                if(icebergs.contains(getCaseFromPoint(p).getPoint())){
                    if(getCaseFromPoint(p).getScoreVoisin() < min){
                        min = getCaseFromPoint(p).getScoreVoisin();
                        res.clear();
                        resultat.clear();
                        res.add(voisinOriginal);
                        resultat.add(coordinatesToText(source.x, source.y)+"-"+coordinatesToText(voisinOriginal.x,voisinOriginal.y));
                    }
                    else if(getCaseFromPoint(p).getScoreVoisin() == min){
                        res.add(voisinOriginal);
                        resultat.add(coordinatesToText(source.x, source.y)+"-"+coordinatesToText(voisinOriginal.x,voisinOriginal.y));
                    }
                }

                ArrayList<Point> adj = stringsToPoints(getPossibleMoves(p));
                for(Point pAdj : adj){
                    Case c = getCaseFromPoint(pAdj);
                    if(!c.isVisited()){
                        c.setIsVisited(true);
                        c.setScoreVoisin(getCaseFromPoint(p).getScoreVoisin() + 1);
                        queue.add(pAdj);
                    }
                }
            }
            clearVisitedAndScore();
        }

        return resultat;
    }

}
