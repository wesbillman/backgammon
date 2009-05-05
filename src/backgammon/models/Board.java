package backgammon.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author wes.billman
 */
public class Board {
    public static final int NUM_PIPS = 26;
    private List<Pip> pips;

    public Board() {
        pips = new ArrayList<Pip>(NUM_PIPS);
        for (int i = 0; i < NUM_PIPS; i++) {
            Pip pip = new Pip();
            pips.add(pip);
        }
    }

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
