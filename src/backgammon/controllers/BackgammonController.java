
package backgammon.controllers;

import backgammon.models.*;
import backgammon.views.*;

/**
 *
 * @author wes.billman
 */
public class BackgammonController {
    private Backgammon model = new Backgammon();
    private BackgammonView view = new BackgammonView();

    public void init() {
        start();
    }

    private void start() {
        model.init();
        view.displayBanner();
        while(model.getNumPlayers() == -1) {
            view.getGameType();
        }
        
    }
}
