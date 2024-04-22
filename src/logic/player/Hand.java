package logic.player;

import logic.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand {
    private int HandSize;
    private ArrayList<Card> CardList;
    private ArrayList<Card> SelectedCards;

    public Hand(int handSize) {
        this.HandSize = handSize;
        CardList = new ArrayList<>();
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

    public void viewHand(){
        for(Card card:CardList){
            System.out.println(card.getRank().toString() + " of " + card.getSuit().toString()+"\n");
        }
    }

    public ArrayList<Card> getSelectedCards() {
        return SelectedCards;
    }

    public void setSelectedCards(ArrayList<Card> selectedCards) {
        SelectedCards = selectedCards;
    }

    public int getHandSize() {
        return HandSize;
    }

    public void setHandSize(int handSize) {
        HandSize = handSize;
    }

    public ArrayList<Card> getCardList() {
        return CardList;
    }

    public void setCardList(ArrayList<Card> cardList) {
        CardList = cardList;
    }

    public void sortCardList(ArrayList<Card> cardList) {
        Collections.sort(cardList, new Comparator<Card>() {
            public int compare(Card card1, Card card2) {
                if (card1.getRank().ordinal() != card2.getRank().ordinal()) {
                    return card1.getRank().ordinal() - card2.getRank().ordinal();
                } else {
                    return card1.getSuit().ordinal() - card2.getSuit().ordinal();
                }
            }
        });
    }
}