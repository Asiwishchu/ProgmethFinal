package logic.player;

import application.AlertHandler;
import application.Rank;
import application.Suit;
import logic.card.Card;
import logic.game.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private ArrayList<Boolean> deckDisplay ;

    AlertHandler alertHandler;

    public Deck() {
        cards = new ArrayList<Card>();
        deckDisplay = new ArrayList<>(52);
    }


    // Method to initialize the deck
    public void initDeck() {
        cards = new ArrayList<Card>();
        deckDisplay = new ArrayList<>(52);
        for (int i = 0; i < 52; i++) {
            deckDisplay.add(null);
        }
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
                deckDisplay.set((suit.ordinal()*13) + rank.ordinal(), true); // set card in deck view to true
            }
        }
        shuffleDeck();
    }

    // Method to shuffle the deck
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    // Method to draw a card from the top of the deck
    public Card drawCard() {
        if (cards.isEmpty()) {
            initDeck();
            System.out.println("Deck is Empty");
            alertHandler.initializeAlert("Deck is empty,\nCreate new deck", Config.BLUE);
        }
        deckDisplay.set((cards.getLast().getSuit().ordinal()*13) + cards.getLast().getRank().ordinal(), false); // set card in deck view to false
        return cards.removeLast();
    }

    public ArrayList<Boolean> getDeckDisplay() {
        return deckDisplay;
    }
}
