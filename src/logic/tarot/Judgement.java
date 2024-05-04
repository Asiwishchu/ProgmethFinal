package logic.tarot;

import logic.game.GameController;

public class Judgement extends HighCostTarot{

    @Override
    public void useAbility() {
        GameController.getInstance().setCurrentMult((GameController.getInstance().getCurrentMult()*3)/2);
    }

    @Override
    public String getDescription() {
        return "This hand you play x1.5 Multiplier";
    }

    @Override
    public String getName() {
        return "Judgement";
    }
}
