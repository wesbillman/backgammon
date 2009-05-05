/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon.views;

import backgammon.models.*;

/**
 *
 * @author wes.billman
 */
public class BackgammonView {

    public void displayBoard(Board board, int currentPlayer) {
        String s = "";
        if (currentPlayer == Player.PLAYER_0) {
            s += "+-------------------------------------------b\n";
            s += "| 13 14 15 16 17 18 (0)  19 20 21 22 23 24  |\n";
            s += "|-------------------------------------------|\n";
        } else {
            s += "|-------------------------------------------w\n";
            s += "| 12 11 10  9  8  7 (25)  6  5  4  3  2  1  |\n";
            s += "---------------------------------------------\n";
        }

        int i;
        for (i = 1; i < 7; i++) {
            s += displayLine(board, i, true);
        }
        for (i = 6; i > 0; i--) {
            s += displayLine(board, i, false);
        }

        if (currentPlayer == Player.PLAYER_0) {
            s += "|-------------------------------------------|\n";
            s += "| 12 11 10  9  8  7 (25)  6  5  4  3  2  1  |\n";
            s += "+-------------------------------------------+\n";
        } else {
            s += "|-------------------------------------------|\n";
            s += "| 13 14 15 16 17 18 (0)  19 20 21 22 23 24  |\n";
            s += "+-------------------------------------------+\n";
        }

        System.out.print(s);
    }

    private String displayLine(Board b, int iLevel, boolean isBlackSide) {
        int i;                          // loop counter
        boolean bDisplayDot = false;    // display dots on the line
        String s = "|";                 // string to represent the line

        if (iLevel < 6) {
            bDisplayDot = true;
        }

        if (isBlackSide) {      // 1 indexed positions
            for (i = 13; i <= 18; i++) {        // outer board
                s += displayChecker(b, i, iLevel, bDisplayDot);
            }

            s += " | ";
            if (b.getPips().get(0).getNumCheckers() >= iLevel) {
                s += "B|";
            } else {
                s += " |";
            }

            for (i = 19; i <= 24; i++) {        // inner board
                s += displayChecker(b, i, iLevel, bDisplayDot);
            }

        } else {            // display whites side of the board
            for (i = 12; i >= 7; i--) {     // outer board
                s += displayChecker(b, i, iLevel, bDisplayDot);
            }

            s += " | ";
            if (b.getPips().get(25).getNumCheckers() >= iLevel) {
                s += "W|";
            } else {
                s += " |";
            }

            for (i = 6; i >= 1; i--) {      // inner board
                s += displayChecker(b, i, iLevel, bDisplayDot);
            }
        }
        return s + "  |\n";
    }

    private String displayChecker(Board b, int iPip, int iLevel, boolean bDot) {
        String s = "  ";
        if (b.getPips().get(iPip).getNumCheckers(Player.PLAYER_0) >= iLevel) {
            s += "B";
        } else if (b.getPips().get(iPip).getNumCheckers(Player.PLAYER_1) >= iLevel) {
            s += "W";
        } else if (bDot) {
            s += ".";
        } else {
            s += " ";
        }

        return s;
    }
}
