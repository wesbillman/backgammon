package backgammon.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wesbillman
 */
public class Dice {
    private List<Integer> dice;

    public Dice() {
        dice = new ArrayList<Integer>();
    }

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

    public int[] getRoll() {
        if(dice.size() == 0) return null;
        
        int[] roll = new int[dice.size()];

        for(int i = 0; i < dice.size(); i++) {
            roll[i] = dice.get(i);
        }

        return roll;
    }

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
