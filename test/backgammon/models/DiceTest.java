/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package backgammon.models;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wesbillman
 */
public class DiceTest {

    public DiceTest() {
    }


    /**
     * Test of roll method, of class Dice.
     */
    @Test
    public void testRoll() {
        Dice instance = new Dice();
        int[] result = instance.roll();
        while(result.length != 2) {
            result = instance.roll();
        }
        assertNotSame(result[0], result[1]);

        while(result.length != 4) {
            result = instance.roll();
        }
        assertEquals(result[0], result[1]);
        assertEquals(result[1], result[2]);
        assertEquals(result[2], result[3]);
    }

    @Test
    public void testToString() {
        Dice instance = new Dice();
        int[] expResult = null;
        int[] result = instance.roll();
        assertNotNull(instance.toString());
    }

    /**
     * Test of getRoll method, of class Dice.
     */
    @Test
    public void testGetRoll() {
        Dice instance = new Dice();
        assertNull(instance.getRoll());

        assertNotNull(instance.roll());
        int[] result = instance.getRoll();
        assertNotNull(result);
        assertTrue(result.length >= 2);
    }

    /**
     * Test of removeDie method, of class Dice.
     */
    @Test
    public void testRemoveDie() {
        Dice instance = new Dice();
        int[] dice = instance.roll();
        int expResult = dice.length - 1;
        int[] result = instance.removeDie(dice[0]);
        assertEquals(expResult, result.length);
    }

}