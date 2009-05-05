/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package backgammon.models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wes.billman
 */
public class BackgammonTest {

    public BackgammonTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getScore method, of class Backgammon.
     */
    @Test
    public void testGetScore() {       
        Backgammon instance = new Backgammon();        
        int result = instance.getScore(Player.PLAYER_0);
        assertEquals(15, result);

        result = instance.getScore(Player.PLAYER_1);
        assertEquals(15, result);
    }

}