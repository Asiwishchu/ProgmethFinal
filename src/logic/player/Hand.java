package logic.player;

import logic.card.Card;

import java.util.ArrayList;
import java.util.Comparator;

public class Hand {
    private int HandSize;
    private ArrayList<Card> CardList;
    private ArrayList<Card> SelectedCards;

    private static final Comparator<Card> cardComparator = (Card p, Card q) -> {
        if (p.getRank() != q.getRank()) {
            return (p.getRank().ordinal() < q.getRank().ordinal() ? -1 : 1);
        }
        if (p.getSuit() != q.getSuit()) {
            return (p.getSuit().ordinal() < q.getSuit().ordinal() ? -1 : 1);
        }
        return 0;
    };

    public Hand(int handSize) {
        this.HandSize = handSize;
        CardList = new ArrayList<>();
        SelectedCards = new ArrayList<>();
    }

    public void initHand() {
        this.HandSize = 7;
        CardList = new ArrayList<>();
    }

    public void fillHand(Deck deck) {
        while (CardList.size() < HandSize) {
            Card card = deck.drawCard();
            CardList.add(card);
        }
    }

    public ArrayList<Card> getSelectedCards() {
        return SelectedCards;
    }

    public void setSelectedCards(ArrayList<Card> selectedCards) {
        SelectedCards = selectedCards;
    }

    public int getHandSize() {
        return Math.min(9 ,Math.max(0, HandSize)); // 0 < Hand size < 9
    }

    public void setHandSize(int handSize) {
        HandSize = Math.min(9 ,Math.max(0, handSize)); // 0 < Hand size < 9
    }

    public ArrayList<Card> getCardList() {
        return CardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        CardList = cardList;
    }

    public static void sortCardList(ArrayList<Card> cards) {
        cards.sort(cardComparator);
    }
}