package logic.tarot;

import logic.game.GameController;

public class TheEmpress extends LowCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() + 20);
    }

    @Override
    public String getDescription() {
        return "This hand +20 Chips when play.";
    }

    @Override
    public String getName() {
        return "The Empress";
    }
}
