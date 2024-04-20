package logic.action;
import application.HandType;
import logic.game.GameController;
import logic.player.Hand;
import logic.game.Deck;
import logic.card.Card;

import java.util.ArrayList;

public class Play {
    private Hand hand;
    private Deck deck;

    public Play(Hand hand, Deck deck){
        this.hand = GameController.getInstance().getHand();
        this.deck = GameController.getInstance().getDeck();

    }

    public void playRound(ArrayList<Card> selectedCards){
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

    public HandType HandTypeClassify(ArrayList<Card> selectedCards){
        HandType handType = HandType.HighCard;
        return handType;
    }

}
