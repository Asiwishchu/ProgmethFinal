package logic.tarot;

import logic.game.Config;
import logic.game.GameController;

public class TheTower extends HighCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().getAlert().initializeAlert("Goal -15%", Config.GREEN);
        GameController.getInstance().getBlind().setReqScore((GameController.getInstance().getBlind().getReqScore() * 15) / 100);
    }

    @Override
    public String getDescription() {
        return "When scoring GOAL -15%";
    }

    @Override
    public String getName() {
        return "The Tower";
    }
}
