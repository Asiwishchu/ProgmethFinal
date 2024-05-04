package logic.tarot;

import application.HandType;
import logic.game.Actions;
import logic.game.GameController;

public class TheSun extends HighCostTarot{

    @Override
    public void useAbility() {
        if(Actions.HandTypeClassify(GameController.getInstance().getPlayer().getHand().getSelectedCards()).equals(HandType.FourOfAKind))
            GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() * 3);
    }

    @Override
    public String getDescription() {
        return "If your played is FOUR OF A KIND, x3 Multiplier";
    }

    @Override
    public String getName() {
        return "The Sun";
    }
}
