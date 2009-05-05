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
public class CheckerTest {

    public CheckerTest() {
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
     * Test of getPlayer method, of class Checker.
     */
    @Test
    public void testGetPlayer() {        
        Player expResult = null;
        Checker instance = new Checker(expResult);
        Player result = instance.getPlayer();
        assertEquals(expResult, result);
    }

}