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
public class PlayerTest {

    public PlayerTest() {
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
     * Test of getId method, of class Player.
     */
    @Test
    public void testGetId() {
        Player instance = new Player(Player.PLAYER_0);
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        instance = new Player(Player.PLAYER_1);
        result = instance.getId();
        assertEquals(1, result);
    }

}