
package backgammon.models;

/**
 *
 * @author wesbillman
 */
public class Backgammon {
    private Board board;
    private Player player0, player1;
    private int numPlayers;

    public Backgammon() {
        board = new Board();
    }

    public void init() {
        player0 = new Player(Player.PLAYER_0);
        player1 = new Player(Player.PLAYER_1);
        board.init(player0, player1);
        setNumPlayers(-1);
    }

    public int getScore(int playerId) {
        int score = 0;
        for(Pip pip : board.getPips()) {
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
}
