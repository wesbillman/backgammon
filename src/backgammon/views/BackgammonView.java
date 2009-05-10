package backgammon.views;

import backgammon.controllers.InputController;
import backgammon.models.*;

public class BackgammonView {

    /**
     * Display a welcome banner to the user
     */
    public void displayBanner() {
        System.out.println();
        System.out.println("*********************************************");
        System.out.println(" Welcome to Backgammon (console) version 1.2 ");
        System.out.println("*********************************************");
    }

    /**
     * Display a goodbye message to the user
     */
    public void displayGoodbye() {
        System.out.println();
        System.out.println("*********************************************");
        System.out.println("       Thanks for playing Backgammon         ");
        System.out.println("*********************************************");
    }

    /**
     * Display any invalid option that the user selected
     * This method is reused for any invalid type.
     * @param option that was not valid
     */
    public void displayInvalidOption(String option) {
        System.out.println();
        System.out.println("'" + option + "' is not a valid option");
    }

    /**
     * Display any generic message to the user
     * @param message to display
     */
    public void displayMessage(String message) {
        System.out.print(message);
    }

    /**
     * Display the game start information
     * @param dice values
     * @param firstPlayer
     */
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

    /**
     * Display the game complete information
     * @param p0Score
     * @param p1Score
     */
    public void displayGameComplete(int p0Score, int p1Score) {
        System.out.println();
        System.out.println("*********************************************");
        System.out.println("                Game Complete                ");
        System.out.println("Final Score:");
        System.out.println("  Black: " + p0Score);
        System.out.println("  White: " + p1Score);
        System.out.println("*********************************************");
    }

    /**
     * Display the current board.  This should display differently
     * based on who is the current player.
     * @param board
     * @param currentPlayer
     */
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
            s += displayLine(board, i, true);
        }
        for (i = 6; i > 0; i--) {
            s += displayLine(board, i, false);
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

    /**
     * Display the current status of the game.  This information
     * is generally displayed after the board.
     * @param player0score
     * @param player1score
     * @param dice
     */
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

    /**
     * Display one line of the board
     * @param b the board
     * @param iLevel the current level of the board
     * @param isBlackSide flag for blackside
     * @return
     */
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

            if (b.getPips().get(25).getNumCheckers(Player.PLAYER_0) >= iLevel) {
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
            } else {
                s += " |";
            }

            for (i = 6; i >= 1; i--) {      // inner board
                s += displayChecker(b, i, iLevel, bDisplayDot);
            }
        }
        return s + "  |\n";
    }

    /**
     * Display an individual checker.
     * @param b the board
     * @param iPip the pip
     * @param iLevel the current level
     * @param bDot fill in with a dot?
     * @return
     */
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

    /**
     * Request game type information from the user.
     * @return userInput
     */
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

    /**
     * Request a move from the user
     * @param player 
     * @return moveInput
     */
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
