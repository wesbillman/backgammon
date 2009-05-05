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
public class BoardTest {

    public BoardTest() {
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
     * Test of init method, of class Board.
     */
    @Test
    public void testInit() {
        Board instance = new Board();
        Player player0 = new Player(Player.PLAYER_0);
        Player player1 = new Player(Player.PLAYER_1);
        instance.init(player0, player1);        
    }

}