package backgammon.models;

public class Checker {
    //A reference to the owning player
    private Player player;

    //Creates a new checker with an owner
    public Checker(Player player) {
        this.player = player;
    }

    //Gets the owning player
    public Player getPlayer() {
        return player;
    }
}
