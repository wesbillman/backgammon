package backgammon.models;

import java.util.ArrayList;
import java.util.List;

public class Pip {
    List<Checker> checkers;
    public Pip() {
        checkers = new ArrayList<Checker>();
    }

    /**
     * Adds a checker to this pip
     * @param checker to add
     * @return true if checker was added successfully
     */
    public boolean addChecker(Checker checker) {
        return checkers.add(checker);
    }

    /**
     * Add a group of checkers
     * @param player to add checkers to
     * @param numCheckers to add to this pip
     * @return true if checker was added successfully
     */
    public boolean addCheckers(Player player, int numCheckers) {
        for(int i = 0; i < numCheckers; i++) {
            Checker checker = new Checker(player);
            if(!addChecker(checker)) return false;
        }

        return true;
    }

    /**
     * Remove a checker from this pip
     * @param checker to remove
     * @return true if the checker was removed successfully
     */
    public boolean removeChecker(Checker checker) {
        return checkers.remove(checker);
    }

    /**
     * Removes a group of checkers from this pip
     * @param player to remove checkers for
     * @param numCheckers to remove
     * @return true if the checker was removed successfully
     */
    public boolean removeCheckers(Player player, int numCheckers) {
        for(int i = 0; i < numCheckers; i++) {
            if(checkers.size() == 0) return false;
            if(checkers.get(0).getPlayer() == player) {
                if(!removeChecker(checkers.get(0))) return false;
            }
        }

        return true;
    }

    /**
     * The number of checkers on this pip
     * @return
     */
    public int getNumCheckers() {
        return checkers.size();
    }

    /**
     * The number of checkers on this pip that belong to
     * the specified player
     * @param playerId to get checkers for
     * @return number of checkers
     */
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