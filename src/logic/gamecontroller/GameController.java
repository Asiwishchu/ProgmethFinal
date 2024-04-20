package src.logic.gamecontroller;

import logic.card.Card;

public class GameController {
    private Deck deck;

    public GameController() {
        deck = new Deck();
    }

    // Method to initialize and shuffle the deck
    public void initAndShuffleDeck() {
        deck.initDeck();
        deck.shuffleDeck();
    }

    // Method to draw a card from the deck
    public Card drawCardFromDeck() {
        return deck.drawCard();
    }
}
