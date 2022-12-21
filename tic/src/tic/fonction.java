package tic;


import java.io.DataInputStream;
import java.io.IOException;

public class fonction {
    public static boolean verif = true;
    public static boolean switche = false;
    public static  String[] zone = new String[9];
    public static  int etape1 = -1;
    public static  int etape2 = -1;
    public static  boolean enemyWin = false;
    static DataInputStream dis;
    public static boolean opposite = false;
    public static boolean won = false;
    public static boolean tie = false;
    public static int errors = 0;

    public static  int[][] wins = new int[][] { 
        { 0, 1, 2 }, 
        { 3, 4, 5 }, 
        { 6, 7, 8 },
         { 0, 3, 6 }, 
         { 1, 4, 7 }, 
         { 2, 5, 8 }, 
         { 0, 4, 8 },
          { 2, 4, 6 } };

	public static void checkWinE() {
		for (int i = 0; i < wins.length; i++) {
			if (verif) {
				if (zone[wins[i][0]] == "X" && zone[wins[i][1]] == "X" && zone[wins[i][2]] == "X") {
					etape1 = wins[i][0];
					etape2 = wins[i][2];
					enemyWin = true;
				}
			} else {
				if (zone[wins[i][0]] == "O" && zone[wins[i][1]] == "O" && zone[wins[i][2]] == "O") {
					etape1 = wins[i][0];
					etape2 = wins[i][2];
					enemyWin = true;
				}
			}
		}
	}
    static void checkForTie() {
		for (int i = 0; i < zone.length; i++) {
			if (zone[i] == null) {
				return;
			}
		}
		tie = true;
	}
    

	static void checkWin() {
		for (int i = 0; i < wins.length; i++) {
			if (verif) {
				if (zone[wins[i][0]] == "O" && zone[wins[i][1]] == "O" && zone[wins[i][2]] == "O") {
					etape1 = wins[i][0];
					etape2 = wins[i][2];
					won = true;
				}
			} else {
				if (zone[wins[i][0]] == "X" && zone[wins[i][1]] == "X" && zone[wins[i][2]] == "X") {
					etape1 = wins[i][0];
					etape2 = wins[i][2];
					won = true;
				}
			}
		}
	}
    static void tick() {
		if (errors >= 10) opposite = true;

		if (!switche && !opposite) {
			try {
				int space = dis.readInt();
				if (verif) zone[space] = "X";
				else zone[space] = "O";
				checkWinE();
				checkForTie();
				switche = true;
			} catch (IOException e) {
				e.printStackTrace();
				errors++;
			}
		}
	}
}

