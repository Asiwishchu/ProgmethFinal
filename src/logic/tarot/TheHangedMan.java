package logic.tarot;

import logic.game.Config;
import logic.game.GameController;
import logic.player.Player;

public class TheHangedMan extends MediumCostTarot{

    @Override
    public void useAbility() {
        if (GameController.getInstance().getDiscard() >= 1) {
            GameController.getInstance().setDiscard(GameController.getInstance().getDiscard() - 1);
            GameController.getInstance().setCurrentMult((GameController.getInstance().getCurrentMult() * 3) / 2);
        }
        else{
            GameController.getInstance().setMoney(GameController.getInstance().getMoney()+3); //return money if dont have discard
            GameController.getInstance().getAlert().initializeAlert("You are out of discard!", Config.YELLLOW);
        }
    }

    @Override
    public String getDescription() {
        return "-1 Discard, x1.5 Multiplier";
    }

    @Override
    public String getName() {
        return "The Hanged Man";
    }
}
