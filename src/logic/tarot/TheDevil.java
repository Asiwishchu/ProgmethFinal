package logic.tarot;

import logic.game.GameController;

public class TheDevil extends HighCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().setMoney(Math.max(0, GameController.getInstance().getMoney() - 5));
        GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() * 2);
    }

    @Override
    public String getDescription() {
        return "-5 Money (Cannot less than 0), x2 Multiplier";
    }

    @Override
    public String getName() {
        return "The Devil";
    }
}
