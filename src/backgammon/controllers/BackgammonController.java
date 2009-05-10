
package backgammon.controllers;

import backgammon.models.*;
import backgammon.views.*;

/**
 *
 * @author wes.billman
 */
public class BackgammonController {
    private Backgammon model = new Backgammon();
    private BackgammonView view = new BackgammonView();

    /**
     * Initialize the Controller, this will kickoff the start
     * sequence to begin gameplay.
     */
    public void init() {
        start();
    }

    /**
     * Start the controller.
     * This will initialize the model and should be called
     * to start each new game.
     */
    private void start() {
        model.init();
        view.displayBanner();
        while(model.getNumPlayers() == -1) {
            String gameType = view.getGameType();
            if(gameType.toUpperCase().startsWith("E")) {
                exit();
            } else if (gameType.equals("0")) {
                model.setNumPlayers(0);
            } else if (gameType.equals("1")) {
                model.setNumPlayers(1);
            } else if (gameType.equals("2")) {
                model.setNumPlayers(2);
            } else {
                view.displayInvalidOption(gameType);
            }
        }

        view.displayMessage("Starting game with " + model.getNumPlayers() +
                " human players.\n");
        view.displayGameStart(model.getDice(), model.getCurrentPlayer().getId());
        play();
    }

    /**
     * Play the game.
     * This will continue to play until there is a winner.
     * Once the game is complete start() will be called again
     * to allow for continuous games.
     */
    private void play() {

        while(!model.isGameComplete()) {
            view.displayBoard(model.getBoard(), model.getCurrentPlayer().getId());
            view.displayStatus(model.getScore(Player.PLAYER_0),
                    model.getScore(Player.PLAYER_1),
                    model.getDice());
            
            int iStart, iDest;
            if(model.getNumPlayers() > model.getCurrentPlayer().getId()) {
                String[] move = view.getMove(model.getCurrentPlayer().getId());
                try{
                    iStart = Integer.parseInt(move[0]);
                    iDest = Integer.parseInt(move[1]);
                    String moveResult = model.moveChecker(iStart, iDest);
                    if(moveResult != null) view.displayMessage(moveResult + "\n");
                } catch( NumberFormatException ex) {
                    view.displayInvalidOption(move[0] + " or " + move[1]);
                    continue;
                }
            } else {
                int[] move = new int[2];
                move = model.getFirstAvailableMove();
                if(move != null) {
                    iStart = move[0];
                    iDest = move[1];
                    model.moveChecker(iStart, iDest);
                } else {
                    model.switchPlayer();
                }
            }
        }
        view.displayGameComplete(model.getScore(Player.PLAYER_0),
                model.getScore(Player.PLAYER_1));
        start();
    }

    /**
     * Exit the game.
     */
    private void exit() {
        view.displayGoodbye();
        System.exit(0);
    }
}
