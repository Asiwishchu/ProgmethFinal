package logic.action;

import logic.card.Card;
import logic.player.Deck;
import logic.game.GameController;
import logic.player.Hand;

import java.util.ArrayList;

public class Discard {
    private ArrayList<Card> cardlist;
    private Hand hand;
    private Deck deck;

    private ArrayList<Card> selectedCards;


    public Discard(){
        this.cardlist = GameController.getInstance().getHand().getCardList();
        this.hand = GameController.getInstance().getHand();
        this.deck = GameController.getInstance().getDeck();
        this.selectedCards = GameController.getInstance().getHand().getSelectedCards();
    }

    public void discardRound(ArrayList<Card> selectedCards){
        //remove & refill
        cardlist.removeAll(selectedCards);
        hand.fillHand(deck);
        System.out.println("Your hand for next round: ");
        hand.viewHand();
    }
}
