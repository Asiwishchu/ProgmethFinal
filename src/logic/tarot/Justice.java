package logic.tarot;

import application.HandType;
import logic.game.GameController;

public class Justice extends MediumCostTarot{
    @Override
    public void useAbility() {
        GameController.getInstance().setCurrentHandType(HandType.FullHouse);
    }

    @Override
    public String getDescription() {
        return "This hand you play is FULL HOUSE";
    }

    @Override
    public String getName() {
        return "Justice";
    }
}
