package logic.game;
import application.HandType;
import application.Rank;
import application.Suit;
import logic.card.Card;
import logic.player.Hand;
import logic.player.Player;

import java.util.ArrayList;

public class Actions {
    public static HandType HandTypeClassify(ArrayList<Card> cards){
        Hand.sortCardList(cards);
        if (isRoyalFlush(cards)) return HandType.RoyalFlush;
        else if (isStraightFlush(cards)) return HandType.StraightFlush;
        else if (isFourOfAKind(cards)) return HandType.FourOfAKind;
        else if (isFullHouse(cards)) return HandType.FullHouse;
        else if (isFlush(cards)) return HandType.Flush;
        else if (isStraight(cards)) return HandType.Straight;
        else if (isThreeOfAKind(cards)) return HandType.ThreeOfAKind;
        else if (isTwoPairs(cards)) return HandType.TwoPair;
        else if (isPair(cards)) return HandType.Pair;
        else return HandType.HighCard;
    }

    // Method to check if the hand is a royal flush
    private static boolean isRoyalFlush(ArrayList<Card> cards) {
        return isStraightFlush(cards) && cards.get(4).getRank() == Rank.ACE;
    }

    // Method to check if the hand is a straight flush
    private static boolean isStraightFlush(ArrayList<Card> cards) {
        return isStraight(cards) && isFlush(cards);
    }

    // Method to check if the hand contains four of a kind
    private static boolean isFourOfAKind(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size() - 3; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank() && cards.get(i).getRank() == cards.get(i + 2).getRank()
                    && cards.get(i).getRank() == cards.get(i + 3).getRank()) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hand is a full house
    private static boolean isFullHouse(ArrayList<Card> cards) {
        boolean three = false;
        boolean pair = false;
        ArrayList<Card> cardPair = cards;

        for (int i = 0; i < cardPair.size() - 2; i++) {
            if (cardPair.get(i).getRank() == cardPair.get(i + 1).getRank() && cardPair.get(i).getRank() == cardPair.get(i + 2).getRank() && cards.size() == 5) {
                three = true;
                cardPair.remove(i+2);
                cardPair.remove(i+1);
                cardPair.remove(i);
                break;
            }
        }
        if(three && cardPair.get(0).getRank() == cardPair.get(1).getRank()) pair = true;

        return three && pair;
    }

    // Method to check if the hand is a flush
    private static boolean isFlush(ArrayList<Card> cards) {
        Suit suit = cards.getFirst().getSuit();
        if(cards.size() < 5) return false;
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the hand is a straight
    private static boolean isStraight(ArrayList<Card> cards) {
        if(cards.size() < 5) return false;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank().ordinal() != cards.get(i + 1).getRank().ordinal() - 1) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the hand contains three of a kind
    public static boolean isThreeOfAKind(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size() - 2; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank() && cards.get(i).getRank() == cards.get(i + 2).getRank()) {
                return true;
            }
//            else if (cards.get(2).getRank() == cards.get(4).getRank()){
//                return false;
//            }
        }
        return false;
    }

    // Method to check if the hand contains two pairs
    private static boolean isTwoPairs(ArrayList<Card> cards) {
        int pairsCount = 0;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank()) {
                pairsCount++;
                i++;
            }
        }
        return pairsCount == 2;
    }

    // Method to check if the hand contains a pair
    public static boolean isPair(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getRank() == cards.get(i + 1).getRank()) {
                return true;
            }
        }
        return false;
    }
}
