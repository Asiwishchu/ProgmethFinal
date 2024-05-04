package logic.tarot;

import application.HandType;
import logic.game.Actions;
import logic.game.GameController;

public class TheWorld extends HighCostTarot{

    @Override
    public void useAbility() {
        if(Actions.HandTypeClassify(GameController.getInstance().getPlayer().getHand().getSelectedCards()).equals(HandType.RoyalFlush))
            GameController.getInstance().getPlayer().setScore(GameController.getInstance().getPlayer().getScore() + ((GameController.getInstance().getStage().getReqScore()*70)/100)); //player score + 70% of blind
    }

    @Override
    public String getDescription() {
        return "If you play ROYAL FLUSH this hand, you gain 70% of Blind score!!!";
    }

    @Override
    public String getName() {
        return "The World";
    }
}
