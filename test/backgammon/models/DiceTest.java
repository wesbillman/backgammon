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
        int[] expResult = null;
        int[] result = instance.roll();
        assertNotNull(result);
        assertTrue(result.length >= 2);
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
        System.out.println(instance.toString());
        int expResult = dice.length - 1;
        System.out.println(dice[0]);
        int[] result = instance.removeDie(dice[0]);

        System.out.println(instance.toString());
        assertEquals(expResult, result.length);
    }

}