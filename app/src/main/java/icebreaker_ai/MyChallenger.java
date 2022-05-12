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
        blackPoints.set(blackPoints.indexOf(new Point(ligneOrigine, colonneOrigine)), new Point(ligneDestination, colonneDestination));
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
            res += " WINS !\n";
            res += "WWWWWWWWWWWWWNWWWWWWWWWWWWWWWWWWWWWWWWWWWWMWWWWWWWWWWWWWWMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"+
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWMWWMWWWWWWWWWMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"+
            "WWWWMMMMWNNXKKNMMWWWMWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"+
            "WWWMMNx:;,'...xMMWWWWWWMWWWWWWWWWWWWWWWWWWWWMMMWOlllodxkOO0KXNWMMMMMMMMWNNWMMWWWWWWWWWWWWWWWWWWWWWWW"+
            "WWWMMWo .co;  lWMMWWWWWWWWMMN000OO0000000000KWMNc.,,'.   ....lOkddooooc;,,kMMMMMMMMMMMMMMMMMMMMWWWWW"+
            "WWWWMMk..,kx. ;KMMWWWWWWWWMMk...,c,...':'...:KMK,,d0Nx.  .cc.co. .lolloo' cNW0doooollllcccc:xWMMWWWW"+
            "WWWWMMK,  cO, .kMMWWWWWWWWMMx.  ,Ok.'okl.   ,KMk..;kK0x. 'Oo.dO.'kd.  .oO,;KWl ..   ...  ...:XMMWWWW"+
            "WWWWMMNc  ,Oc  oWMMWWWWWWWMMx.   l0kOd.     ,KWd.,d0o,kx.:Kc.kK,c0;    .ko'kWo.;l'  ck:. ;l.;XMMWWWW"+
            "WWWWWMMx. .xd. :NMMWWWWWWWMMx.   lXkdxl.    ,0Nc cKK: ,Odx0;,KNc'Od.   ;0c.dWd. :c.,l;l;.l: ,KMMWWWW"+
            "WWWWWMMO'  lk' '0MMWWWWWWWMMx.   lO' :O:    '0K, oW0,  ,OWk.cNWd.'ddlloxc. lNd. .c:l: ,lcl. ,0MMWWWW"+
            "WWWWWMMX:  ,d, .xMMWWWMWWWMMx.   ck' .xd.   'Ok. .;'.   ,d:.oWMk. .;loo:;:cxNx.  'dl.  ;x;  'OMMWWWW"+
            "WWMWWMMWo   ....dWMWWWMWWWMMx.   ..   ;xdll;,OXkxddollc:;,';OMMN0O0KKXNWWMMMMk.   ..    .   .kMMWWWW"+
            "WWWWWWMMKddxkO0KNMMWWWWWWWMMx.         .',,.,OMMMMMMMMMMWWNNWMMMMMMMMMMMMMMMM0c,;;;:::::ccccoKMMWWWW"+
            "WWWWWWWMMMMMMMWWWWWWWWWWWWMMXkxxxxxxxxxxxxxxONMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWWWWMMMMMMMMMMMMWWWWW"+
            "WWWWWWWWWWWWWWWWWWWWWWWWWNWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWWWWWWWWWWWWWWWWWWWW"+
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWNNNNNNNNNNNNNNNNNNNWMMWNWWWWWWWWWWWWWWWWWWW"+
            "WWWWWWWWWWWWWWWWWWWWWWWMMNKK0OOkxdoolcckWMMNNNWWMMMMMMMWx,''''''''''''''''':0MMMMMMMMWWNXNMMWWWWWWWW"+
            "WWWWWWWWMMMMMMMMMMMMMMMMMx'.....   .,. :NMWd,;;;::cclldKo 'clllllc:'       .kNkollc:;;;,'oNMMWWWWWWW"+
            "WWWWWMMW0dddddoooollllcxNx. ,dO;   ;0l ,KMX:.okdlllol..ko .oKd;;;;lkl.     .kN:.:oooooo:.;KMMWWWWWWW"+
            "WWWWWMMN: ..   ..    ..,KO'  ;Kd.',cKd..OM0, :0c...''.,0o  ;O:    .dx.     .kWo.,kKc.... 'OMMWWWWWWW"+
            "WWMWWMMNc.:l.  :k:  ,o.,0X;  .kKdoodKO..xMk. lXxoolo, :Xo  ,0klllokd'      .kMx. cKxlool..xMMWWWWWWW"+
            "WWWWWMMWl  cc ,l:l, cc 'ONl  .dO'   oK, lWd .dk,.,,,. oWo  ,0d,,,;xx.      .OMO' ;Kk:;,'. oWMMWWWWWW"+
            "WWWWWMMWo  .l;c: ;l:l, .OMd.  lO,   ;x, :Xl .k0l:::;..xWo  ;O:    ,Oc      .OMK; 'Ox'',;,.cNMMWWWWWW"+
            "WWWWWWMWd   ;ko.  :kc. .kMk.  ...       ,Ox;;loooooo,'OWo  ,O:    .xo      .OMNc .cxooooc':XMMWWWWWW"+
            "WWMWWWMMd.  .'.   ...  .xMK;...'',;:ccloxXMWWNXXK0OOO0NWo  .'.     :kc'... .OMWOlloodxxkO0KWMMWWWWWW"+
            "WWWWWWMMk'...''''',,,,;:0MWX0KXXNWWMMMMMMMMWWWWWWWWWMMMWo           'clll:..OMMMMMMMMMMMMWWWWWWWWWWW"+
            "WWWWWWMMWXXXXXNNNNNWWWWWMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWMMOccccccccccccccccccoKMMWWWWWWWWWWWWWWWWWWWWW"+
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMMMMMMMMMMMMMMMMMWWWWWWWWWWWWWWWWWWWWW"+
            "WWWWWWWWWWWWWNNNNNNNNNNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMWkcclodxkO0KXXNWMMMMMMMMWWWWWWWWWWW"+
            "WWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX: '::,.  ...'',;:clod0WMWWWMWWWWWW"+
            "WWWWWWWWWWMMWWNNNXXKK0000OOkkxxddddKMMKxoooooddoddddddddddddddxKM0' ;xXO'        .:;.  oWMWWWWWWWWWW"+
            "WWWWWWWWWWMMk;''''.......         .dMMx.       ..''''..       .dMx.  '0x.        :Xd. .kMMWWWWWWWWWW"+
            "WWWWWWWWWWMMx.  ....         ':.   oWMk.    .;dOXNNNNX0x:.    .xWl   :Xo         oNc  ,KMMWWWWWWWWWW"+
            "WWWWWWWWWWMMk.  ;dkk,       :0x.   lWMx.   ;ONXkl:;;:lxXW0:.  .dK;   oX:        .xK,  cNMMWWWWWWWWWW"+
            "WWWWWWWWWWMMO.    .o0o.   .l0o.    cNMx.  cXWO,        ,kWNl. .dO.  .k0'        '0k. .dMMMWWMWWWWWWW"+
            "WWWWWWWWWWMM0'      ;Ok, .d0c.     :XMx. '0M0'          .kMK, .dd.  ,Kx.        :Xd. .OMMWWWWWWWWWWW"+
            "WWWWWWWWWWMMK,       .dOdkO;       ,KMx. :NWd.           cNWl .dl   ,Kx.       .dXc  ;KMMWWWWWWWWWWW"+
            "WWWWWWWWWWMMX:        .cK0,        '0Mx. :XWo            cNWl .l;   .oKl.     .c0d.  lWMMWWWWWWWWWWW"+
            "WWWWWWWWWWMMNc         .kk.        .OMx. '0MO'          .kMK; .;.    .ckkocccokkc.  .xMMWWWWWWWWWWWW"+
            "WWWWWWWWWWMMWl         .xO.        .kMx.  cXWk'        'kWNl. .:l::,,'.':loolc,.    'OMMWWWWWWWWWWWW"+
            "WWWWWWWWWWWMWo         .d0,        .xMx.  .:0WXxc;,,;cxXWKc.  .xMMMWNXXK0Okxdolcc:;,lXMMWWWWWWWWWWWW"+
            "WWWWWWWWWWWMMd.         ',.        .dWx.    .cxKNNNNNNKkc.    .xMMMWWWWWWMMMMMMMMMWNWMMWWWWWWWWWWWWW"+
            "WWWWWWWWWWWMMO:,;;::cccclloooddxxkkOXWx.       .',;;,'.       .xMMWNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"+
            "WWWWWWWWWWWMMWWWWMMMMMMMMMMMMMMMMMMMMMKoclllcclllllllllclllclll0MMWNWWWWWWWWWWWWWWWWWWWWMWWWWWWWWWWW"+
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"+
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNWWWWWWWMMMMMMMMMMMMMMMMMMMMWWWMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"+
            "WWWWWWMMMMMMMMMMMMMMMMMMMMWWWWWWWWWWWWWWWWWWMMNXKK00OOkxdoolcc:;;,oXMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"+
            "WWWWMMXkkkkkkkkkkkkkkkkkkKWMWNNWMMNXNWMMMMMMMMx'.....        .,;. '0MMMMWWWWMMMMMMMMMMMMMMMMMWWWWWWW"+
            "WWWWMMo                  cNMMNWMMXc',;:lodxO0Nx. :dxkl.      ,0O' .kMMMWx,;;:::ccclllooodddkXMMWWWWW"+
            "WWWMMWo                  cNMMWWMMk. ..      ,OO' ':ckNd.     oNo. .dMMMN:  ......          .OMMWWWWW"+
            "WWWMMMo                  cNMMMMMWo  :xkx,   :XK;    .xXo.   'OK,   cNMMX; ,kK000OOOkkxxx:. '0MMWWWWW"+
            "WWWMMWo    ';;'.         cNMMMMMK;  .;O0,  .dWNc     .xXo.  cNd.   ;KMMK, .;oKM0lllooodd:. ,KMMWWWWW"+
            "WWWMMWo   .oKWO'         cNMMMMMk.   ;Kx.  'OMWd.     .kXl..kX;    'OMM0'   '0Wo           ;XMMWWWWW"+
            "MWWMMMo     cNO'         cNMMMMWl    oNc   cNMMk.      .kXlcXx.    .xMMO.   ,KWd'.......   :NMMWWWWW"+
            "WWWMMMo     :XO'         cNMMMMK,   .k0'  .xMMM0'       .kXXXc      oWMk.   :XWNXKKKK00d.  lWMMWWWWW"+
            "WWWMMWo     :XO'         cNMMMMx.   :Xd.  ,0MMMX;        .odc.      cNMx.   cNNo,;;::cc;.  oWMMWWMWW"+
            "WWWMMWo     :XO'         cNMMMNc   .dX:   lNMMMWl   ....'',;::cllodd0WMd.   lWK;          .dMMWWWMWW"+
            "WWWMMWo     :N0,......   cNMMM0'   .OO.  .xMMMMMXkkO00KXNNWWMMMMMMMMMMWo    oWK:....      .xMMWWWWWW"+
            "WWWMMWo     :XN0OOOOOx'  cNMMMd.   :Xo   ;KMMWWWMMWWMWWWWWWWWWWWWWWWMMNc   .dMWX000OOkkd' .kMMWWWWWW"+
            "WWWMMWo     .,;;;;;;;,.  cNMMX:    .;.   lWMMWNWWWWWWWWWWWWWWWWWWWWWMMN:    '::ccclllool. 'OMMWWWWWW"+
            "WWWWMMx'..''.............oWMMXd;,'..    .kMMWWWMWWMMWWWWWWWWWWWWWWWWMMWOddooollcccc:::;;,,lKMMWWWWWW"+
            "WWWWMMWXXXXXXXXXXXXXXXXXXNMMMMMMWNK0OkxoxXMMWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMMMMMMMMMMWWWWWMMWWWWWWW"+
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"+
            "WWMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW";
        }

        return res;
    }

    @Override
    public String defeat() {
        String res = redScore == MAXSCORE ? "BLACK" : blackScore == MAXSCORE ? "RED" : "";

        if (res != "") {
            res += " LOSES !\n";
            res +=
            "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXkokWM\n"+
            "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMXOo:cldNM\n"+
            "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWk:;;:loxNM\n"+
            "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWNXKKXNM0,.;::loxNM\n"+
            "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN0kdolc::coxx'.,::loxNM\n"+
            "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWNXKOK0dc:;::;;;::cll,..::loxNM\n"+
            "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXK0OOOOO0KXWMWKOOkkxxdoolccl:..,::l;...';:ldc..;:llxNM\n"+
            "MMMMMMMMMMMMMMMMMMMMMMMMMWXOkkkO0NWMMMMMMMNOxoc::;;;;;::cldl,,;;:::::c:ccxl. ,::dl;' .;:ldd,.,:llxNM\n"+
            "MMMMMMMMMMMMMMMMMMMMMMMXkl:;;;;:coxONMMMNOl:::cccccc:cccco; .,cccccccc:::c:. '::oodo',:codko..;llxNM\n"+
            "MMMMMMMMMMMMMMMMMMMMMW0c;:::::::clllokXNx::ccccl:'....,;ll.  ':cccc,......;, .;:clc;::cooxXO'.;lokNM\n"+
            "MMMMMMWWMMMMMMMMMMMMWO:;c:cc:::cccclo::o::cccclo,.',;,'..... .:ccll,',;;:xXd .;ccc:c:cookNMN:.,cokNM\n"+
            "MMMN0kdldXMMMMMMMMMMXl;c::cl,..';cccco:..:cccccooodxxxdoc:kl .:ccllcclcloKMk..;:cc:::cooxXMWo..',xWM\n"+
            "WKxl::d: ;XMMMMMMMMMO::cccoc. ,dl;ccclo,..:ccccccllllllllokl .:ccccccccdx0MO'.,:ccc:::clooONO. .,oKW\n"+
            "k;;:::ld,.lNMMMMMMMMk::cccd: .OMO::cccdc  ..',;::::ccccclcc,..:ccc:;;,,;lKMK, ,:ccl:,;::coodkl',clo0\n"+
            "0c;::::od'.dWMMMMMMM0::cccdl.'0M0c:cccdc  .,'..  ...,ccccco:..:ccc,....;kNWK; ':c:ll..;:::clol'':cl0\n"+
            "WO:;::::oo..kWMMMMMMNo;cccldc.dNx::ccld; .,cllcc:;,.':ccccoc..:ccc;,::cloddx: .:::co, .,:::::,. ..lN\n"+
            "MWk:;:::coc.'OMMMMMNKd,;ccccolcl::cccol. .:cc:::::::ccccclo, .:cccc::::::ood: .:::cdc   .'...';;,oXM\n"+
            "MMWk;;:::co:.,0NKkdllc,.;:cccccccccclo' .,cccccccccccccllc'  .:cccccc::::oodc .;;,,,co, .,:okKWWWMMM\n"+
            "MMMNx;::::co:.;l:;::coo'..;::ccccccl:.  ...',,;;;::::::,. .. .,,,,''''''.';kl     .'xWXOKNMMMMMMMMMM\n"+
            "MMMMNx;::::cc:;:::::::oo' ':,'','''.  'xOo:'...     ....;oOc  ........''';xNOlodxO0XWMMMMMMMMMMMMMMM\n"+   
            "MMMMMNx;:::::::::::::;,;. .lko:,''';lkXMMMWNK0kxxddxxO0XWMWKOO0000KKXXXNNWMMMMMMMMMMMMMMMMMMMMMMMMMM\n"+
            "MMMMMMNx;;::::::;,'..  .':lkNMMWNNNWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"+
            "MMMMMMMNx;;:;,'.. ..;okKNWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"+
            "MMMMMMMMWk;..  .;oOXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"+
            "MMMMMMMMMWKdclkXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n"+
            "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n";
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
            // board.get(p2.x).get(p2.y).setIsVisited(true);
            if (board.get(p2.x).get(p2.y).getValue().equals("o")) {
                resIfIceberg.add(coordinatesToText(p.x, p.y) + "-" + coordinatesToText(p2.x, p2.y));
            }
            res.add(coordinatesToText(p.x, p.y) + "-" + coordinatesToText(p2.x, p2.y));
        }
    }

    boolean isIceberg = false;

    private Case getCaseFromPoint(Point p) {
        return board.get(p.x).get(p.y);
    }

    private Point getPointFromCase(Case c) {
        return new Point(c.getPoint().x, c.getPoint().y);
    }

    private void clearVisitedAndScore() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                board.get(i).get(j).setScore(0);
                board.get(i).get(j).setScoreVoisin(0);
                board.get(i).get(j).setIsVisited(false);
            }
        }
    }

    private ArrayList<Point> bfs(Point start) {
        LinkedList<Point> queue = new LinkedList<Point>();
        ArrayList<Point> res = new ArrayList<>();
        queue.add(start);
        Case cStart = getCaseFromPoint(start);
        cStart.setScore(0);

        while (queue.size() != 0) {
            start = queue.poll();
            if (getCaseFromPoint(start).getValue().equals("o")) {
                res.add(start);
            }

            ArrayList<Point> adj = stringsToPoints(getPossibleMoves(start));
            for (Point p : adj) {
                Case c = getCaseFromPoint(p);
                if (!c.isVisited()) {
                    c.setIsVisited(true);
                    c.setScore(getCaseFromPoint(start).getScore() + 1);
                    queue.add(p);
                }
            }
        }
        clearVisitedAndScore();
        ArrayList<Point> positionIcebergProche = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (Point p : res) {
            if (getCaseFromPoint(p).getScore() < min) {
                positionIcebergProche.clear();
                positionIcebergProche.add(p);
                min = getCaseFromPoint(p).getScore();
            } else if (getCaseFromPoint(p).getScore() == min) {
                positionIcebergProche.add(p);
            }
        }
        return positionIcebergProche;
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
    
    @Override
    public Set<String> possibleMoves(String role) {
        Set<String> listPossibleMove = new HashSet<>();
        if (role.equals("BLACK")) {
            for (Point p : blackPoints) {
                ArrayList<Point> voisins = new ArrayList<Point>(stringsToPoints(getPossibleMoves(p)));

                listPossibleMove.addAll(bfsVoisin(p, voisins, bfs(p)));
            }
        }else if (role.equals("RED")) {
            for (Point p : redPoints) {
                ArrayList<Point> voisins = new ArrayList<Point>(stringsToPoints(getPossibleMoves(p)));

                listPossibleMove.addAll(bfsVoisin(p, voisins, bfs(p)));
            }
        }
        return listPossibleMove;
    }

    private Set<String> bfsVoisin(Point source, ArrayList<Point> voisins, ArrayList<Point> icebergs) {
        LinkedList<Point> queue = new LinkedList<Point>();
        // ArrayList<Boolean> visited = new ArrayList<>();
        ArrayList<Point> res = new ArrayList<Point>();

        Set<String> resultat = new HashSet<>();
        int min = Integer.MAX_VALUE;

        for (Point voisin : voisins) {
            Point voisinOriginal = voisin;
            queue.add(voisin);
            Case cStart = getCaseFromPoint(voisin);
            cStart.setScore(0);
            while (queue.size() != 0) {
                Point p = queue.poll();
                // todo : change "B" --> getRole
                if (icebergs.contains(getCaseFromPoint(p).getPoint())) {
                    if (getCaseFromPoint(p).getScoreVoisin() < min) {
                        min = getCaseFromPoint(p).getScoreVoisin();
                        res.clear();
                        resultat.clear();
                        res.add(voisinOriginal);
                        resultat.add(coordinatesToText(source.x, source.y) + "-"
                                + coordinatesToText(voisinOriginal.x, voisinOriginal.y));
                    } else if (getCaseFromPoint(p).getScoreVoisin() == min) {
                        res.add(voisinOriginal);
                        resultat.add(coordinatesToText(source.x, source.y) + "-"
                                + coordinatesToText(voisinOriginal.x, voisinOriginal.y));
                    }
                }

                ArrayList<Point> adj = stringsToPoints(getPossibleMoves(p));
                for (Point pAdj : adj) {
                    Case c = getCaseFromPoint(pAdj);
                    if (!c.isVisited()) {
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
