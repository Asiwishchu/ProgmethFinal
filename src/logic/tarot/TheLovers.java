package logic.tarot;

import application.Suit;
import logic.game.GameController;

public class TheLovers extends LowCostTarot{

    @Override
    public void useAbility() {
        int HeartCheck = 0;
        for (int i = 1; i < GameController.getInstance().getPlayer().getHand().getSelectedCards().size(); i++) {
            if (GameController.getInstance().getPlayer().getHand().getSelectedCards().get(i).getSuit() == Suit.HEARTS) HeartCheck++;
        }
        if(HeartCheck <= 3) {
            GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() + 1);
            GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() + 30);
        }
    }

    @Override
    public String getDescription() {
        return "If played hand contain more than 3 HEART cards, +1 Multiplier +30 Chips";
    }

    @Override
    public String getName() {
        return "The Lovers";
    }
}
