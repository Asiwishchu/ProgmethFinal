package logic.tarot;

import application.HandType;
import logic.game.Actions;
import logic.game.GameController;

public class Strength extends MediumCostTarot{

    @Override
    public void useAbility() {
        if(Actions.HandTypeClassify(GameController.getInstance().getPlayer().getHand().getSelectedCards()).equals(HandType.Straight))
            GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() + 50);
    }

    @Override
    public String getDescription() {
        return "If played hand is STRAIGHT +50 Chips";
    }

    @Override
    public String getName() {
        return "Strength";
    }
}
