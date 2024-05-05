package logic.tarot;

import logic.game.Config;
import logic.game.GameController;
import logic.player.Player;

public class TheFool extends LowCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().getPlayer().getHand().setHandSize(GameController.getInstance().getPlayer().getHand().getHandSize()+1); //+1 Hand Size
        GameController.getInstance().getAlert().initializeAlert("+1 Hand size", Config.GREEN);
        GameController.getInstance().setHandSizeReset(Math.max(GameController.getInstance().getHandSizeReset(), 1)); //Skip reset for 1 turn
    }

    @Override
    public String getDescription() {
        return "Next turn +1 HANDSIZE. (Max at 9)";
    }

    @Override
    public String getName() {
        return "The Fool";
    }
}
