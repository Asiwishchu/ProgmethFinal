package logic.gamecontroller;

import application.deck.Rank;
import application.deck.Suit;
import logic.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;
    private Random random;

    public Deck() {
        cards = new ArrayList<>();
        random = new Random();
    }

    // Method to initialize the deck
    public void initDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    // Method to shuffle the deck
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    // Method to draw a card from the top of the deck
    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        return cards.remove(0);
    }
}
