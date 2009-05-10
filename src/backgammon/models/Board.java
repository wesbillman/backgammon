package backgammon.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wes.billman
 */
public class Board {
    //Total number of pips should be 26
    public static final int NUM_PIPS = 26;

    //A list of all pips on the board.
    private List<Pip> pips;

    public Board() {
        pips = new ArrayList<Pip>(NUM_PIPS);
        for (int i = 0; i < NUM_PIPS; i++) {
            Pip pip = new Pip();
            pips.add(pip);
        }
    }

    /**
     * Initializes the board to a starting state.
     * Should build board based on backgammon rules
     * 
     * @param player0
     * @param player1
     */
    public void init(Player player0, Player player1) {
        //Initialize Player0's board
        getPips().get(6).addCheckers(player0, 5);
        getPips().get(8).addCheckers(player0, 3);
        getPips().get(13).addCheckers(player0, 5);
        getPips().get(24).addCheckers(player0, 2);
        
        //Initialize Player1's board
        getPips().get(1).addCheckers(player1, 2);
        getPips().get(12).addCheckers(player1, 5);
        getPips().get(17).addCheckers(player1, 3);
        getPips().get(19).addCheckers(player1, 5);
    }

    @Override
    public String toString() {
        String pipStr = "";
        for (int i = 0; i < getPips().size(); i++) {
            pipStr += i + " " + getPips().get(i).toString() + "\n";
        }
        return pipStr;
    }

    /**
     * @return the pips
     */
    public List<Pip> getPips() {
        return pips;
    }
}
