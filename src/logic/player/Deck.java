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

    public Deck() {
        cards = new ArrayList<Card>();
    }


    private static Comparator<Card> cardComparator = (Card p, Card q) -> {
        if (p.getRank() != q.getRank()) {
            return (p.getRank().ordinal() < q.getRank().ordinal() ? -1 : 1);
        }
        if (p.getSuit() != q.getSuit()) {
            return (p.getSuit().ordinal() < q.getSuit().ordinal() ? -1 : 1);
        }
        return 0;
    };


    // Method to initialize the deck
    public void initDeck() {
        cards = new ArrayList<Card>();
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
        else {
            return cards.removeLast();
        }
    }

    public List<Card> getCards() {
        return cards;
    }


}
