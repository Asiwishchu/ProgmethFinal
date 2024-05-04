package logic.player;

import application.Rank;
import application.Suit;
import logic.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private ArrayList<Card> deckArrange ;

    public Deck() {cards = new ArrayList<Card>();}


    // Method to initialize the deck
    public void initDeck() {
        cards = new ArrayList<Card>();
        deckArrange = new ArrayList<Card>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
                deckArrange.add(new Card(suit, rank));
            }
        }
    }

    public ArrayList<Card> getInitDeck() {
        return deckArrange;
    }

    // Method to shuffle the deck
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    // Method to draw a card from the top of the deck
    public Card drawCard() {
        if (cards.isEmpty()) {
            initDeck();
            throw new IllegalStateException("Deck is empty");
        }
        else {
            return cards.removeLast();
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
