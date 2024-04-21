package logic.player;
import application.deck.Rank;
import application.deck.Suit;
import logic.card.Card;
import logic.game.Deck;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private int HandSize;
    private ArrayList<Card> CardList;

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
            System.out.println(card.getRank().toString() + " of " + card.getSuit().toString());
        }
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

    // Method to calculate the score of the hand
    public int calculateScore() {
//        // Sort the hand by rank to simplify scoring
//        Collections.sort(hand);

        if (isRoyalFlush()) {
            return 1000; // Placeholder score for royal flush
        } else if (isStraightFlush()) {
            return 900; // Placeholder score for straight flush
        } else if (isFourOfAKind()) {
            return 800; // Placeholder score for four of a kind
        } else if (isFullHouse()) {
            return 700; // Placeholder score for full house
        } else if (isFlush()) {
            return 600; // Placeholder score for flush
        } else if (isStraight()) {
            return 500; // Placeholder score for straight
        } else if (isThreeOfAKind()) {
            return 400; // Placeholder score for three of a kind
        } else if (isTwoPairs()) {
            return 300; // Placeholder score for two pairs
        } else if (isPair()) {
            return 200; // Placeholder score for a pair
        } else {
            return 100; // Placeholder score for single high card
        }
    }

    // Method to check if the hand is a royal flush
    private boolean isRoyalFlush() {
        // Check for a straight flush with the highest card being an Ace (Rank.ACE)
        return isStraightFlush() && hand.get(4).getRank() == Rank.ACE;
    }

    // Method to check if the hand is a straight flush
    private boolean isStraightFlush() {
        // Check for a straight and a flush simultaneously
        return isStraight() && isFlush();
    }

    // Method to check if the hand contains four of a kind
    private boolean isFourOfAKind() {
        for (int i = 0; i < hand.size() - 3; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank() && hand.get(i).getRank() == hand.get(i + 2).getRank()
                    && hand.get(i).getRank() == hand.get(i + 3).getRank()) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hand is a full house
    private boolean isFullHouse() {
        boolean three = false;
        boolean pair = false;

        for (int i = 0; i < hand.size() - 2; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank() && hand.get(i).getRank() == hand.get(i + 2).getRank()) {
                three = true;
            }
        }

        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                pair = true;
            }
        }

        return three && pair;
    }

    // Method to check if the hand is a flush
    private boolean isFlush() {
        // Check if all cards have the same suit
        Suit suit = hand.get(0).getSuit();
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the hand is a straight
    private boolean isStraight() {
        // Check for the presence of consecutive ranks
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank().ordinal() != hand.get(i + 1).getRank().ordinal() - 1) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the hand contains three of a kind
    private boolean isThreeOfAKind() {
        for (int i = 0; i < hand.size() - 2; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank() && hand.get(i).getRank() == hand.get(i + 2).getRank()) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hand contains two pairs
    private boolean isTwoPairs() {
        int pairsCount = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                pairsCount++;
                i++; // Skip the next card since it's part of the pair
            }
        }
        return pairsCount == 2;
    }

    // Method to check if the hand contains a pair
    private boolean isPair() {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()) {
                return true;
            }
        }
        return false;
    }

}