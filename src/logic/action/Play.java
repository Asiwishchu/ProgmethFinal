package logic.action;
import application.HandType;
import application.Rank;
import application.Suit;
import logic.game.GameController;
import logic.player.Hand;
import logic.game.Deck;
import logic.card.Card;

import java.util.ArrayList;

public class Play {

    private ArrayList<Card> handcards;
    private Hand hand;
    private Deck deck;

    private ArrayList<Card> selectedCards;

    public Play(Hand hand, Deck deck){
        this.handcards = GameController.getInstance().getHand().getCardList();
        this.hand = GameController.getInstance().getHand();
        this.deck = GameController.getInstance().getDeck();

    }

    public void playRound(ArrayList<Card> selectedCards){
        ArrayList<Card> cardsToRemove = new ArrayList<>();

        if(handcards.contains(selectedCards)) {
            if (selectedCards.size() >= 1 && selectedCards.size() <= 5) {
                for (Card selectedCard : selectedCards) {
                    cardsToRemove.add(selectedCard);
                }

                handcards.removeAll(cardsToRemove);
                hand.fillHand(deck);
                System.out.println("Your hand for next round: " + handcards);
            }
        }
    }

    public HandType HandTypeClassify(ArrayList<Card> selectedCards){
        if (isRoyalFlush()) {
            return HandType.RoyalFlush;
        } else if (isStraightFlush()) {
            return HandType.StraightFlush;
        } else if (isFourOfAKind()) {
            return HandType.FourOfAKind;
        } else if (isFullHouse()) {
            return HandType.FullHouse;
        } else if (isFlush()) {
            return HandType.Flush;
        } else if (isStraight()) {
            return HandType.Straight;
        } else if (isThreeOfAKind()) {
            return HandType.ThreeOfAKind;
        } else if (isTwoPairs()) {
            return HandType.TwoPair;
        } else if (isPair()) {
            return HandType.Pair;
        } else {
            return HandType.HighCard;
        }
    }

    // Method to check if the hand is a royal flush
    private boolean isRoyalFlush() {
        return isStraightFlush() && handcards.get(4).getRank() == Rank.ACE;
    }

    // Method to check if the hand is a straight flush
    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    // Method to check if the hand contains four of a kind
    private boolean isFourOfAKind() {
        for (int i = 0; i < hand.getHandSize() - 3; i++) {
            if (handcards.get(i).getRank() == handcards.get(i + 1).getRank() && handcards.get(i).getRank() == handcards.get(i + 2).getRank()
                    && handcards.get(i).getRank() == handcards.get(i + 3).getRank()) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hand is a full house
    private boolean isFullHouse() {
        boolean three = false;
        boolean pair = false;

        for (int i = 0; i < hand.getHandSize() - 2; i++) {
            if (handcards.get(i).getRank() == handcards.get(i + 1).getRank() && handcards.get(i).getRank() == handcards.get(i + 2).getRank()) {
                three = true;
            }
        }

        for (int i = 0; i < hand.getHandSize() - 1; i++) {
            if (handcards.get(i).getRank() == handcards.get(i + 1).getRank()) {
                pair = true;
            }
        }

        return three && pair;
    }

    // Method to check if the hand is a flush
    private boolean isFlush() {
        // Check if all cards have the same suit
        Suit suit = handcards.get(0).getSuit();
        for (int i = 1; i < hand.getHandSize(); i++) {
            if (handcards.get(i).getSuit() != suit) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the hand is a straight
    private boolean isStraight() {
        // Check for the presence of consecutive ranks
        for (int i = 0; i < hand.getHandSize() - 1; i++) {
            if (handcards.get(i).getRank().ordinal() != handcards.get(i + 1).getRank().ordinal() - 1) {
                return false;
            }
        }
        return true;
    }

    // Method to check if the hand contains three of a kind
    private boolean isThreeOfAKind() {
        for (int i = 0; i < hand.getHandSize() - 2; i++) {
            if (handcards.get(i).getRank() == handcards.get(i + 1).getRank() && handcards.get(i).getRank() == handcards.get(i + 2).getRank()) {
                return true;
            }
        }
        return false;
    }

    // Method to check if the hand contains two pairs
    private boolean isTwoPairs() {
        int pairsCount = 0;
        for (int i = 0; i < hand.getHandSize() - 1; i++) {
            if (handcards.get(i).getRank() == handcards.get(i + 1).getRank()) {
                pairsCount++;
                i++;
            }
        }
        return pairsCount == 2;
    }

    // Method to check if the hand contains a pair
    private boolean isPair() {
        for (int i = 0; i < hand.getHandSize() - 1; i++) {
            if (handcards.get(i).getRank() == handcards.get(i + 1).getRank()) {
                return true;
            }
        }
        return false;
    }

}
