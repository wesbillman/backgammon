/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package backgammon.views;

import backgammon.models.Backgammon;
import backgammon.models.Board;
import backgammon.models.Player;
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
public class BackgammonViewTest {

    public BackgammonViewTest() {
    }

    /**
     * Test of displayBoard method, of class BackgammonView.
     */
    @Test
    public void testDisplayBoard() {        
        Board board = new Board();
        board.init(new Player(Player.PLAYER_0), new Player(Player.PLAYER_1));
        BackgammonView instance = new BackgammonView();
        instance.displayBoard(board, Player.PLAYER_0);
        instance.displayBoard(board, Player.PLAYER_1);
    }

}