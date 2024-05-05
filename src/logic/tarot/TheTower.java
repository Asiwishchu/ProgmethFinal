package logic.tarot;

import logic.game.GameController;

public class TheTower extends HighCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().getBlind().setReqScore((GameController.getInstance().getBlind().getReqScore() * 70) / 100);
        GameController.getInstance().setTheTowerSetter(true);
    }

    @Override
    public String getDescription() {
        return "When scoring this hand required blind score -30%";
    }

    @Override
    public String getName() {
        return "The Tower";
    }
}
