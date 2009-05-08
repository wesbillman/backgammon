
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

        if(iStart == iDest) return "Cannot move to same position";
        
        result = haveCorrectDice(Math.abs(iDest - iStart));
        if (result != null) return result;

        result = isCorrectDirection(iStart, iDest);
        if (result != null) return result;

        return null;
    }

    private String haveCorrectDice(int moveDistance) {
        int diceTotal = 0;
        for(int i = 0; i < dice.getRoll().length; i++) {
            int die = dice.getRoll()[i];
            diceTotal += die;
            if(moveDistance == die) return null;
            if(moveDistance == diceTotal) return null;
        }
        
        return "You do not have the correct dice for this move";
    }

    private String isCorrectDirection(int iStart, int iDest) {
        if(currentPlayer.equals(player0) && iStart > iDest)
            return null;
        if(currentPlayer.equals(player1) && iDest > iStart) {
            return null;
        }

        return "You cannot move backwards.";
    }
}
