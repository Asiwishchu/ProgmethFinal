package logic.action;

import logic.card.Card;
import logic.game.Deck;
import logic.game.GameController;
import logic.player.Hand;

import java.util.ArrayList;

public class Discard {
    private Hand hand;
    private Deck deck;


    public Discard(Hand hand){
        this.hand = GameController.getInstance().getHand();
        this.deck = GameController.getInstance().getDeck();

    }

    public void discardRound(ArrayList<Card> selectedCards){
        ArrayList<Card> cardsToRemove = new ArrayList<>();

        if(hand.getHand().contains(selectedCards)) {
            if (selectedCards.size() >= 1 && selectedCards.size() <= 5) {
                for (Card selectedCard : selectedCards) {
                    cardsToRemove.add(selectedCard);
                }

                hand.getHand().removeAll(cardsToRemove);
                hand.fillHand(deck);
                System.out.println("Your hand for next round: " + hand.getHand());
            }
        }


    }
}
