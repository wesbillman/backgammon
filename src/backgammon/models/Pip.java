package backgammon.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wesbillman
 */
public class Pip {
    List<Checker> checkers;
    public Pip() {
        checkers = new ArrayList<Checker>();
    }

    public boolean addChecker(Checker checker) {
        return checkers.add(checker);
    }

    public boolean addCheckers(Player player, int numCheckers) {
        for(int i = 0; i < numCheckers; i++) {
            Checker checker = new Checker(player);
            if(!addChecker(checker)) return false;
        }

        return true;
    }

    public boolean removeChecker(Checker checker) {
        return checkers.remove(checker);
    }

    public boolean removeCheckers(Player player, int numCheckers) {
        for(int i = 0; i < numCheckers; i++) {
            if(checkers.size() == 0) return false;
            if(checkers.get(0).getPlayer() == player) {
                if(!removeChecker(checkers.get(0))) return false;
            }
        }

        return true;
    }

    public int getNumCheckers() {
        return checkers.size();
    }

    public int getNumCheckers(int playerId) {
        int numCheckers = 0;
        for(Checker checker : checkers) {
            if(checker.getPlayer().getId() == playerId) {
                numCheckers++;
            }
        }
        return numCheckers;
    }

    @Override
    public String toString() {
        String checkStr = "";
        for(Checker checker : checkers) {
            checkStr += "P" + checker.getPlayer().getId() + " ";
        }
        return checkStr;
    }
}