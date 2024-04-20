package logic.player;
import logic.card.Card;
import logic.game.Deck;

import java.util.ArrayList;

public class Hand {
    private int HandSize;
    private ArrayList<Card> hand;

    public Hand(int handSize) {
        this.HandSize = handSize;
        hand = new ArrayList<>();
    }

    public void initHand() {
        this.HandSize = 7;
        hand = new ArrayList<>();
    }


    public void fillHand(Deck deck) {
        while (hand.size() < HandSize) {
            Card card = deck.drawCard();
            hand.add(card);
        }
    }

    public int getHandSize() {
        return HandSize;
    }

    public void setHandSize(int handSize) {
        HandSize = handSize;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}