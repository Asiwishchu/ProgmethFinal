package logic.tarot;

import application.HandType;
import logic.game.CardClassifier;
import logic.game.GameController;

public class Strength extends MediumCostTarot{

    @Override
    public void useAbility() {
        if(CardClassifier.HandTypeClassify(GameController.getInstance().getPlayer().getHand().getSelectedCards()).equals(HandType.Straight))
            GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() + 50);
    }

    @Override
    public String getDescription() {
        return "If you play STRAIGHT this hand, +50 Chips";
    }

    @Override
    public String getName() {
        return "Strength";
    }
}
