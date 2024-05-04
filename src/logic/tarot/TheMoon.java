package logic.tarot;

import logic.game.GameController;

public class TheMoon extends HighCostTarot{

    @Override
    public void useAbility() {
        if (GameController.getInstance().getDiscard() >= 1) {
            GameController.getInstance().setPlayHand(GameController.getInstance().getPlayHand() + 2);
            GameController.getInstance().setDiscard(GameController.getInstance().getDiscard() - 1);
        }
        else GameController.getInstance().setMoney(GameController.getInstance().getMoney() + 5); //return money if dont have discard
    }

    @Override
    public String getDescription() {
        return "Play HANDS +2 but DISCARD -1";
    }

    @Override
    public String getName() {
        return "The Moon";
    }
}
