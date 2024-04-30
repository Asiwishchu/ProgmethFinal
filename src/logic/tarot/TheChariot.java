package logic.tarot;

import application.HandType;
import logic.game.Actions;
import logic.game.GameController;

public class TheChariot extends MediumCostTarot{

    @Override
    public void useAbility() {
        if(Actions.HandTypeClassify(GameController.getInstance().getPlayer().getHand().getSelectedCards()).equals(HandType.Flush))
            GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult()*2);
    }

    @Override
    public String getDescription() {
        return "If you play FLUSH this hand Multiplier x2";
    }

    @Override
    public String getName() {
        return "The Chariot";
    }
}
