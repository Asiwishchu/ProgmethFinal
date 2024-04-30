package logic.tarot;

import application.HandType;
import logic.game.Actions;
import logic.game.GameController;

public class TheMagician extends LowCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() + 2);
    }

    @Override
    public String getDescription() {
        return "This hand +2 Mult when play";
    }

    @Override
    public String getName() {
        return "The Magician";
    }
}
