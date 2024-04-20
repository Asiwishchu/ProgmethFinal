package logic.player;
import logic.card.Card;
import logic.gamecontroller.GameController;
import logic.gamecontroller.Deck;

import java.util.ArrayList;

public class Hand {
    private int HandSize;

    private ArrayList<Card> hand;

    public Hand(int handSize) {
        this.HandSize = handSize;
        hand = new ArrayList<>();

    }

    public void fillHand(Deck deck) {
        while (hand.size() < HandSize) {
            Card card = deck.drawCard();
            hand.add(card);
        }
    }
}