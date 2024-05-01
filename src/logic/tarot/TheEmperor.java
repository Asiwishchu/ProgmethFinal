package logic.tarot;

import logic.game.GameController;

public class TheEmperor extends LowCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().setIncome(GameController.getInstance().getIncome() + 1);
    }

    @Override
    public String getDescription() {
        return "Income +1";
    }

    @Override
    public String getName() {
        return "The Emperor";
    }
}
