
package backgammon.models;

/**
 *
 * @author wesbillman
 */
public class Backgammon {
    private Board board;
    private Dice dice;
    private Player player0, player1, currentPlayer;
    private int numPlayers;

    public Backgammon() {
        board = new Board();
    }

    public void init() {
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

    public int getScore(int playerId) {
        int score = 0;
        for(Pip pip : getBoard().getPips()) {
            score += pip.getNumCheckers(playerId);
        }
        return score;
    }

    /**
     * @return the numPlayers
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * @param numPlayers the numPlayers to set
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
            getActualPip(0).addCheckers(nonCurrentPlayer(), 1);
        }

        getActualPip(iDest).addCheckers(currentPlayer, 1);

        dice.removeDie(moveDistance);
        
        if(dice.getRoll() == null) {
            currentPlayer = nonCurrentPlayer();
            dice.roll();
        }
        return null;
    }

    private String haveCorrectDie(int moveDistance) {
        for(int i = 0; i < dice.getRoll().length; i++) {
            int die = dice.getRoll()[i];
            if(moveDistance == die) return null;
        }
        
        return "You do not have the correct dice for this move";
    }

    private String validatePositions(int iStart, int iDest) {
        if(iStart < iDest) return "You cannot move backwards";

        if(getActualPip(iStart).getNumCheckers(currentPlayer.getId()) == 0) {
            return "You do not have a check on pip " + iStart;
        }

        if(getActualPip(iDest).getNumCheckers(nonCurrentPlayer().getId()) > 1) {
            return "Desitination Pip is occupied";
        }
        return null;
    }

    private Pip getActualPip(int index) {
        //Just return pip by id
        if(currentPlayer == player0) return board.getPips().get(index);

        //Return a convert pip value for player 1
        return board.getPips().get((Board.NUM_PIPS-1) - index);
    }

    private Player nonCurrentPlayer() {
        if(currentPlayer == player0) return player1;

        return player0;
    }
}
