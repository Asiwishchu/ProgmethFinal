package logic.tarot;

import logic.game.Actions;
import logic.game.GameController;

public class TheHierophant extends LowCostTarot{

    @Override
    public void useAbility() {
        if(Actions.isThreeOfAKind(GameController.getInstance().getPlayer().getHand().getSelectedCards())){
            GameController.getInstance().getPlayer().getHand().setHandSize(GameController.getInstance().getPlayer().getHand().getHandSize()+2); //+1 Hand Size
            GameController.getInstance().setHandSizeReset(Math.max(GameController.getInstance().getHandSizeReset(), 1));
        }
    }

    @Override
    public String getDescription() {
        return "If played hand contain THREE OF A KIND next turn +2 Hand size.(Max at 9)";
    }

    @Override
    public String getName() {
        return "The Hierophant";
    }
}
