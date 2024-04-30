package logic.tarot;

import logic.game.GameController;

public class TheHermit extends MediumCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().setMoney(GameController.getInstance().getMoney()*2);
    }

    @Override
    public String getDescription() {
        return "Your MONEY get multiply by 2";
    }

    @Override
    public String getName() {
        return "The Hermit";
    }
}
