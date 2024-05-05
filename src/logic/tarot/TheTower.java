package logic.tarot;

import application.AlertHandler;
import gui.SideBar;
import logic.game.Config;
import logic.game.GameController;

public class TheTower extends HighCostTarot{
    AlertHandler alertHandler = GameController.getInstance().getAlertHandler();

    @Override
    public void useAbility() {
        alertHandler.initializeAlert("-30% Reqiured score\nfor 1 turn", Config.GREEN);
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
