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
public class PipTest {

    public PipTest() {
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
     * Test of addChecker method, of class Pip.
     */
    @Test
    public void testAddChecker() {
        Checker checker = null;
        Pip instance = new Pip();
        assertTrue(instance.addChecker(checker));
    }

    @Test
    public void testToString() {
        Checker checker = new Checker(new Player(Player.PLAYER_0));
        Pip instance = new Pip();
        assertTrue(instance.addChecker(checker));
        assertNotNull(instance.toString());
    }

    /**
     * Test of addCheckers method, of class Pip.
     */
    @Test
    public void testAddCheckers() {
        Player player = new Player(Player.PLAYER_0);
        Pip instance = new Pip();
        assertTrue(instance.addCheckers(player, 6));
    }

    /**
     * Test of removeCheker method, of class Pip.
     */
    @Test
    public void testRemoveChecker() {
        Checker checker = null;
        Pip instance = new Pip();
        assertFalse(instance.removeChecker(checker));
        instance.addChecker(checker);
        assertTrue(instance.removeChecker(checker));
    }

    /**
     * Test of removeCheker method, of class Pip.
     */
    @Test
    public void testRemoveCheckers() {
        Player player = new Player(Player.PLAYER_0);
        Pip instance = new Pip();
        assertFalse(instance.removeCheckers(player, 1));
    }

    /**
     * Test of getNumCheckers method, of class Pip.
     */
    @Test
    public void testGetNumCheckers_0args() {
        Pip instance = new Pip();
        Player player = new Player(Player.PLAYER_0);
        int expResult = 0;
        int result = instance.getNumCheckers();
        assertEquals(expResult, result);
        instance.addCheckers(player, 6);
        assertEquals(instance.getNumCheckers(), 6);
        instance.removeCheckers(player, 3);
        assertEquals(instance.getNumCheckers(), 3);
    }

    /**
     * Test of getNumCheckers method, of class Pip.
     */
    @Test
    public void testGetNumCheckers_Player() {
        Player player = new Player(0);
        Checker checker = new Checker(player);
        Pip instance = new Pip();
        int expResult = 0;
        int result = instance.getNumCheckers(player.getId());
        assertEquals(expResult, result);
        instance.addChecker(checker);
        assertEquals(instance.getNumCheckers(), 1);
        assertEquals(instance.getNumCheckers(player.getId()), 1);
    }

}