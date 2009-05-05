
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
}
