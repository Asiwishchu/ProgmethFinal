package logic.tarot;

import logic.game.CardClassifier;
import logic.game.GameController;

public class TheHighPriestess extends LowCostTarot{

    @Override
    public void useAbility() {
        if(CardClassifier.isPair(GameController.getInstance().getPlayer().getHand().getSelectedCards())){
            GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() + 2);
            GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() + 10);
        }
    }

    @Override
    public String getDescription() {
        return "If played hand contain PAIR,\n\n+2 Multiplier +10 Chips";
    }

    @Override
    public String getName() {
        return "The High Priestess";
    }
}
