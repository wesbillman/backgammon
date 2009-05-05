
package backgammon.models;

/**
 *
 * @author wesbillman
 */
public class Backgammon {
    private Board board;
    private Player player0, player1;

    public Backgammon() {
        board = new Board();
        player0 = new Player(Player.PLAYER_0);
        player1 = new Player(Player.PLAYER_1);
        board.init(player0, player1);
    }

    public int getScore(int playerId) {
        int score = 0;
        for(Pip pip : board.getPips()) {
            score += pip.getNumCheckers(playerId);
        }
        return score;
    }
}
