package logic.tarot;

import application.Suit;
import logic.game.GameController;

public class Temperance extends MediumCostTarot{

    @Override
    public void useAbility() {
        boolean club = false;
        boolean diamond = false;
        boolean heart = false;
        boolean spade = false;

        for(int i = 1; i < GameController.getInstance().getPlayer().getHand().getSelectedCards().size(); i++) {
            if (GameController.getInstance().getPlayer().getHand().getSelectedCards().get(i).getSuit() == Suit.CLUBS) club = true;
            if (GameController.getInstance().getPlayer().getHand().getSelectedCards().get(i).getSuit() == Suit.DIAMONDS) diamond = true;
            if (GameController.getInstance().getPlayer().getHand().getSelectedCards().get(i).getSuit() == Suit.HEARTS) heart = true;
            if (GameController.getInstance().getPlayer().getHand().getSelectedCards().get(i).getSuit() == Suit.SPADES) spade = true;
        }
        if(club && diamond && heart && spade) {
            GameController.getInstance().setPlayHand(GameController.getInstance().getPlayHand() + 1);
        }
    }

    @Override
    public String getDescription() {
        return "If played hand contain ALL SUITS, +1 HANDS";
    }

    @Override
    public String getName() {
        return "Temperance";
    }
}
