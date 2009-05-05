package backgammon.models;

/**
 *
 * @author wesbillman
 */
public class Player {

    public static final int PLAYER_0 = 0;
    public static final int PLAYER_1 = 1;
    private int id;

    public Player(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}