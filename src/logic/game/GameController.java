package logic.game;

import logic.card.Card;
import logic.player.*;

public class GameController {
    private Deck deck;
    private Hand hand;

    public GameController() {
        deck = new Deck();
        hand = new Hand();
    }

    // Method to initialize and shuffle the deck
    public void initAndShuffleDeck() {
        deck.initDeck();
        deck.shuffleDeck();
    }

    public void initPlayer(){

    }

    // Method to draw a card from the deck
    public Card drawCardFromDeck() {
        return deck.drawCard();
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
