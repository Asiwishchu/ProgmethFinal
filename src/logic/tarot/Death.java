package logic.tarot;

import logic.game.GameController;

public class Death extends MediumCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().setMoney(Math.max(0, GameController.getInstance().getMoney() - 2));
        GameController.getInstance().setIncome(GameController.getInstance().getIncome() + 3);
    }

    @Override
    public String getDescription() {
        return "-2 Money (Cannot less than 0), +3 Income";
    }

    @Override
    public String getName() {
        return "Death";
    }
}
