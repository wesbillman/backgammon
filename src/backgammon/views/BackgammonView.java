package backgammon.views;

import backgammon.controllers.InputController;
import backgammon.models.*;

/**
 *
 * @author wes.billman
 */
public class BackgammonView {

    public void displayBanner() {
        System.out.println();
        System.out.println("*********************************************");
        System.out.println(" Welcome to Backgammon (console) version 1.2 ");
        System.out.println("*********************************************");
    }

    public void displayGoodbye() {
        System.out.println();
        System.out.println("*********************************************");
        System.out.println("       Thanks for playing Backgammon         ");
        System.out.println("*********************************************");
    }

    public void displayInvalidOption(String option) {
        System.out.println();
        System.out.println("'" + option + "' is not a valid option");
    }

    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displayGameStart(Dice dice, int firstPlayer) {
        System.out.println("Starting Roll...");
        System.out.println("  Black: " + dice.getRoll()[0]);
        System.out.println("  White: " + dice.getRoll()[1]);

        if(firstPlayer == Player.PLAYER_0) {
            System.out.println("Black goes first.");
        } else {
            System.out.println("White goes first.");
        }
    }

    public void displayBoard(Board board, int currentPlayer) {
        String s = "";
        if (currentPlayer == Player.PLAYER_0) {
            s += "+-------------------------------------------b\n";
            s += "| 13 14 15 16 17 18 (25)  19 20 21 22 23 24 |\n";
            s += "|-------------------------------------------|\n";
        } else {
            s += "+-------------------------------------------w\n";
            s += "| 12 11 10  9  8  7 ( 0)  6  5  4  3  2  1  |\n";
            s += "---------------------------------------------\n";
        }

        int i;
        for (i = 1; i < 7; i++) {
            s += displayLine(board, i, true, currentPlayer);
        }
        for (i = 6; i > 0; i--) {
            s += displayLine(board, i, false, currentPlayer);
        }

        if (currentPlayer == Player.PLAYER_0) {
            s += "|-------------------------------------------|\n";
            s += "| 12 11 10  9  8  7 ( 0)  6  5  4  3  2  1  |\n";
            s += "+-------------------------------------------+\n";
        } else {
            s += "|-------------------------------------------|\n";
            s += "| 13 14 15 16 17 18 (25)  19 20 21 22 23 24 |\n";
            s += "+-------------------------------------------+\n";
        }

        System.out.print(s);
    }

    public void displayStatus(int player0score, int player1score, Dice dice) {
        System.out.print("Black: " + player0score);
        System.out.print("      White: " + player1score);
        System.out.print("     Dice: ");

        int[] aiDice = dice.getRoll();
        for (int i = 0; i < aiDice.length; i++) {
            System.out.print(" " + aiDice[i]);
        }

        System.out.println();
    }

    private String displayLine(Board b, int iLevel, boolean isBlackSide,
            int currentPlayer) {
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
            if (b.getPips().get(25).getNumCheckers(Player.PLAYER_0) >= iLevel) {
                s += "B|";
            }else if (b.getPips().get(0).getNumCheckers(Player.PLAYER_1) >= iLevel) {
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
            if (b.getPips().get(25).getNumCheckers(Player.PLAYER_1) >= iLevel) {
                s += "W|";
            } else if (b.getPips().get(0).getNumCheckers(Player.PLAYER_0) >= iLevel) {
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

    public String getGameType() {
        System.out.println();
        System.out.println("Select Game Type:");
        System.out.println("  0 - Computer vs. Computer");
        System.out.println("  1 - Player vs. Computer");
        System.out.println("  2 - Player vs. Player");
        System.out.println("  E - Exit the application");
        System.out.print("Game Type: ");

        return InputController.readString();
    }

    public String[] getMove(int player) {
        String[] moves = new String[2];
        String playerName = (player == Player.PLAYER_0) ? "Black" : "White";

        System.out.print(playerName + " move from: ");
        moves[0] = InputController.readString();        

        System.out.print(playerName + " move to: ");
        moves[1] = InputController.readString();        

        return moves;
    }
}
