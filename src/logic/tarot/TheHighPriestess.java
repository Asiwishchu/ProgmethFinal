package logic.tarot;

import logic.game.Actions;
import logic.game.GameController;

public class TheHighPriestess extends LowCostTarot{

    @Override
    public void useAbility() {
        if(Actions.isPair(GameController.getInstance().getPlayer().getHand().getSelectedCards())){
            GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() + 2);
            GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() + 10);
        }
    }

    @Override
    public String getDescription() {
        return "If played hand contain PAIR +2 Mult +10 Chips";
    }

    @Override
    public String getName() {
        return "The High Priestess";
    }
}
