package backgammon.models;

import java.util.ArrayList;
import java.util.List;

public class Dice {
    private List<Integer> dice;

    public Dice() {
        dice = new ArrayList<Integer>();
    }

    /**
     * Rolls the dice
     * this will store and return the dice values
     * @return diceValues
     */
    public int[] roll() {
        int die1 = (int)(6 * Math.random() + 1);
        int die2 = (int)(6 * Math.random() + 1);
        dice.clear();
        dice.add(die1);
        dice.add(die2);
        
        if(die1 == die2) {
            dice.add(die1);
            dice.add(die2);
        }

        return getRoll();
    }

    /**
     * Gets the values for the last roll
     * @return diceValues
     */
    public int[] getRoll() {
        if(dice.size() == 0) return null;
        
        int[] roll = new int[dice.size()];

        for(int i = 0; i < dice.size(); i++) {
            roll[i] = dice.get(i);
        }

        return roll;
    }

    /**
     * Removes a die from the values list
     * @param dieValue the die to remove
     * @return diceValue
     */
    public int[] removeDie(int dieValue) {
        Integer removeDie = null;
        for(Integer die : dice) {
            if(die == dieValue) {
                removeDie = die;
                break;
            }
        }

        if(removeDie != null) {
            dice.remove(removeDie);
        }

        return getRoll();
    }

    @Override
    public String toString() {
        String diceStr = "";
        for(Integer die : dice) {
            diceStr += die + " ";
        }
        
        return diceStr;
    }
}
