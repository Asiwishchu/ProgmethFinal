package logic.tarot;

import logic.game.GameController;

public class Judgement extends HighCostTarot{

    @Override
    public void useAbility() {
        if(GameController.getInstance().getPlayHand()<=1)
            GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() * 3);
    }

    @Override
    public String getDescription() {
        return "If you have only 1 HAND left x3 Multiplier!!";
    }

    @Override
    public String getName() {
        return "Judgement";
    }
}
