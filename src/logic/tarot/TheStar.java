package logic.tarot;

import logic.game.GameController;

public class TheStar extends HighCostTarot{

    @Override
    public void useAbility() {
        if(GameController.getInstance().getPlayHand()<=1)
            GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() * 3);
        else GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() + 3);
    }
    
    @Override
    public String getDescription() {
        return "+3 Multiplier BUT If you have only 1 HAND left,\n\nx3 Multiplier instead!!";
    }

    @Override
    public String getName() {
        return "The Star";
    }
}
