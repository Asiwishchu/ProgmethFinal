package utils;

import application.HandType;
import logic.card.Card;
import logic.game.CardClassifier;
import logic.game.GameController;
import logic.tarot.Tarot;

import java.util.ArrayList;

public class GameUtils {

    // Validate Hand
    public static boolean handInputValid(String[] cardSelection) {
        boolean isValid = true;

        if (cardSelection.length == 0 || cardSelection.length > 5 || cardSelection[0].isEmpty()) isValid = false;

        for (int i = 0; i < cardSelection.length; i++) {
            for (int j = (i + 1); j < cardSelection.length; j++) {
                if (cardSelection[i].equals(cardSelection[j])) {
                    isValid = false;
                    break;
                }
            }
            if (!(cardSelection[i].equals("1") || cardSelection[i].equals("2") || cardSelection[i].equals("3") || cardSelection[i].equals("4") || cardSelection[i].equals("5") || cardSelection[i].equals("6") || cardSelection[i].equals("7") || cardSelection[i].equals("8") || cardSelection[i].equals("9")))
                isValid = false;
        }
        return isValid;
    }

    // Get Chip Value
    public static int HandTypeChip(HandType handType) {
        int Chip = 0;

        if (handType.equals(HandType.RoyalFlush)) {
            Chip = 100;
        } else if (handType.equals(HandType.StraightFlush)) {
            Chip = 80;
        } else if (handType.equals(HandType.FourOfAKind)) {
            Chip = 70;
        } else if (handType.equals(HandType.FullHouse)) {
            Chip = 50;
        } else if (handType.equals(HandType.Flush)) {
            Chip = 45;
        } else if (handType.equals(HandType.Straight)) {
            Chip = 40;
        } else if (handType.equals(HandType.ThreeOfAKind)) {
            Chip = 35;
        } else if (handType.equals(HandType.TwoPair)) {
            Chip = 30;
        } else if (handType.equals(HandType.Pair)) {
            Chip = 20;
        } else {
            Chip = 10;
        }
        return Chip;
    }

    // Get Multiplier Value
    public static int HandTypeMult(HandType handType) {
        int Mult = 0;

        if (handType.equals(HandType.RoyalFlush)) {
            Mult = 10;
        } else if (handType.equals(HandType.StraightFlush)) {
            Mult = 8;
        } else if (handType.equals(HandType.FourOfAKind)) {
            Mult = 7;
        } else if (handType.equals(HandType.FullHouse)) {
            Mult = 4;
        } else if (handType.equals(HandType.Flush)) {
            Mult = 4;
        } else if (handType.equals(HandType.Straight)) {
            Mult = 4;
        } else if (handType.equals(HandType.ThreeOfAKind)) {
            Mult = 3;
        } else if (handType.equals(HandType.TwoPair)) {
            Mult = 2;
        } else if (handType.equals(HandType.Pair)) {
            Mult = 2;
        } else {
            Mult = 1;
        }
        return Mult;
    }

    //Calculate Hand Score
    public static String calculateScoreCard() {
        ArrayList<Card> cardListToCalculate = GameController.getInstance().getPlayer().getHand().getSelectedCards();
        if(cardListToCalculate.isEmpty()) return null;
        GameController gameInstance = GameController.getInstance();
        HandType currentHandType = CardClassifier.HandTypeClassify(cardListToCalculate);
        gameInstance.setCurrentHandType(currentHandType);
        gameInstance.setCurrentChips(HandTypeChip(currentHandType));
        gameInstance.setCurrentMult(HandTypeMult(currentHandType));

        for (Card card : cardListToCalculate)
            gameInstance.setCurrentChips(gameInstance.getCurrentChips() + (card.getRank().ordinal() + 2));

        gameInstance.setCurrentHandType(currentHandType);
        //Tarot's ability activating
        if (!GameController.getInstance().getSelectedTarots().isEmpty()) {
            for (Tarot tarot : GameController.getInstance().getSelectedTarots()) {
                tarot.useAbility();
            }
        }
        gameInstance.getSelectedTarots().clear();

        return currentHandType.toString();
    }
}
