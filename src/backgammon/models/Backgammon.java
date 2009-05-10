
package backgammon.models;

/**
 *
 * @author wesbillman
 */
public class Backgammon {
    //The board layout for this game.
    private Board board;

    //the dice model for this game
    private Dice dice;

    //the players and a reference to current player
    private Player player0, player1, currentPlayer;

    //how many human players in this game
    private int numPlayers;

    /**
     * Initialize the backgammon model.
     * This should reset all states so that a new
     * game can begin.
     */
    public void init() {
        board = new Board();
        player0 = new Player(Player.PLAYER_0);
        player1 = new Player(Player.PLAYER_1);
        getBoard().init(player0, player1);
        setNumPlayers(-1);
        dice = new Dice();
        int[] currentDice = dice.roll();
        while(currentDice[0] == currentDice[1]) {
            currentDice = dice.roll();
        }

        currentPlayer = (currentDice[0] > currentDice[1]) ? player0 : player1;
    }

    /**
     * Gets the current score for the specified player
     * @param playerId the player to get score for
     * @return the current score for the player
     */
    public int getScore(int playerId) {
        int score = 0;
        for(int i = 25; i > 0; i--) {
            score += board.getPips().get(i).getNumCheckers(playerId);
            //score += getActualPip(i).getNumCheckers(playerId);
        }
        return score;
    }

    /**
     * @return the number of human players
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * @param numPlayers the number of human players
     */
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @return the dice
     */
    public Dice getDice() {
        return dice;
    }

    /**
     * @return the currentPlayer
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns true if the game is over...
     * @return is game complete
     */
    public boolean isGameComplete() {
        if(getScore(player0.getId()) == 0) return true;
        if(getScore(player1.getId()) == 0) return true;

        return false;
    }

    /**
     * Calculates the first available move.  Used for computer
     * moves.
     * @return moves the move array for a valid move
     *               null if there are none.
     */
    public int[] getFirstAvailableMove() {
        int[] move = new int[2];

        for(int iStart = 25; iStart > 0; iStart--) {
            for(int iDest = 24; iDest >= 0; iDest--) {
                int moveDistance = Math.abs(iStart - iDest);
                if(haveCorrectDie(moveDistance) != null) {
                    continue;
                }
                if(validatePositions(iStart, iDest) == null) {
                    move[0] = iStart;
                    move[1] = iDest;
                    return move;
                }
            }
        }
        return null;
    }

    /**
     * Move a check from start to dest.
     * This will validate the move is acceptable and perform the
     * move.  If the move is not valid it will return an
     * error message for the user.
     *
     * @param iStart the pip to move from
     * @param iDest the pip to move to
     * @return statusMessage
     */
    public String moveChecker(int iStart, int iDest) {
        String result = null;
        int moveDistance = Math.abs(iDest - iStart);

        if(iStart == iDest) return "Cannot move to same position";
        
        result = haveCorrectDie(moveDistance);
        if (result != null) return result;
    
        result = validatePositions(iStart, iDest);
        if (result != null) return result;

        getActualPip(iStart).removeCheckers(currentPlayer, 1);
        if(getActualPip(iDest).getNumCheckers(nonCurrentPlayer().getId()) == 1) {
            getActualPip(iDest).removeCheckers(nonCurrentPlayer(), 1);
            getActualPip(25).addCheckers(nonCurrentPlayer(), 1);
        }
        getActualPip(iDest).addCheckers(currentPlayer, 1);

        dice.removeDie(moveDistance);
        
        if(dice.getRoll() == null) {
            switchPlayer();
        }
        return null;
    }

    /**
     * Force a player change
     */
    public void switchPlayer() {
        currentPlayer = nonCurrentPlayer();
        dice.roll();
    }

    /**
     * Validate the user has the correct die for
     * the requested move
     * @param moveDistance how far to move
     * @return statusMessage
     */
    private String haveCorrectDie(int moveDistance) {
        for(int i = 0; i < dice.getRoll().length; i++) {
            int die = dice.getRoll()[i];
            if(moveDistance == die) return null;
        }
        
        return "You do not have the correct dice for this move";
    }

    /**
     * Validates the requested positions for this move.
     * current validates; direction, start, dest, bar and home.
     * @param iStart
     * @param iDest
     * @return statusMessage
     */
    private String validatePositions(int iStart, int iDest) {
        //Validate direction
        if(iStart < iDest) return "You cannot move backwards";

        //Validate iStart
        if(getActualPip(iStart).getNumCheckers(currentPlayer.getId()) == 0) {
            return "You do not have a check on pip " + iStart;
        }

        //Validate iDest
        if(getActualPip(iDest).getNumCheckers(nonCurrentPlayer().getId()) > 1) {
            return "Desitination Pip is occupied";
        }

        //Validate Bar
        if(iStart != 25) {
            if(getActualPip(25).getNumCheckers(currentPlayer.getId()) > 0) {
                return "You must move off Bar first";
            }
        }

        //Validate Home Board
        if(iDest == 0) {
            int checkersOffHome = 0;
            for(int i = 25; i > 6; i--) {
                checkersOffHome += getActualPip(i).getNumCheckers(currentPlayer.getId());
            }
            if(checkersOffHome > 0) return "You must move all checkers to home first";
        }
        
        return null;
    }

    /**
     * This is a conversion function to covert index to a
     * pip based on the current player.
     * @param index
     * @return pip
     */
    private Pip getActualPip(int index) {
        //Bar and off don't change.
        if(index == 0 || index == 25) {
            return board.getPips().get(index);
        }

        //Just return pip by id
        if(currentPlayer == player0) return board.getPips().get(index);

        //Return a convert pip value for player 1
        return board.getPips().get((Board.NUM_PIPS-1) - index);
    }

    /**
     * Gets the player who is not the currentPlayer
     * @return
     */
    private Player nonCurrentPlayer() {
        if(currentPlayer == player0) return player1;

        return player0;
    }
}
