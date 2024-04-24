package main;


//import javafx.application.Application;
//import javafx.stage.Stage;
import application.HandType;
import logic.card.Card;
import logic.game.Actions;
import logic.game.GameController;
import logic.game.Stage;
import logic.player.Deck;
import logic.player.Hand;
import logic.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//public class Main extends Application{
//    @Override
//    public void start(Stage stage) throws Exception {
//
//    }
public class Main{

    public static void main(String[] args) {
        GameController gameInstance = GameController.getInstance();

        boolean isGameOver = false;
        int totalscore = 0;

        while(!isGameOver) {

            //Initialize round
            gameInstance.getPlayer().getHand().initHand();
            gameInstance.initAndShuffleDeck();

            //Fill first hand
            gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());

            int playround = gameInstance.getPlayer().getPlayRound();
            int discardround = gameInstance.getPlayer().getDiscardRound();

            do {
                //Stage Start
                System.out.println("|---------------------------------------------------------------|");
                System.out.println(gameInstance.getStage().getStageLv() + " Stage: Score at least " + gameInstance.getStage().getReqScore());
                System.out.println("Round score: " + gameInstance.getPlayer().getScore());
                System.out.println("\n{Hands: " + playround + "}   {Discards: " + discardround + "}");
                System.out.println("|---------------------------------------------------------------|");

                //Display current hand
                System.out.println("\nCurrent hand: ");
                for (int i = 0; i < gameInstance.getPlayer().getHand().getCardList().size(); i++) {
                    System.out.print("[" + gameInstance.getPlayer().getHand().getCardList().get(i).getRank() + " Of " + gameInstance.getPlayer().getHand().getCardList().get(i).getSuit() + "] ");
                }

                //Selecting cards from hand
                Scanner scanString = new Scanner(System.in);
                System.out.println("\n\nPick cards from hand! (Enter hand positions of cards, ex. \"1 2 3 4 5\")");
                String[] cardSelection = scanString.nextLine().split(" ");

                //Input failsafe
                boolean isValid;
                do {
                    isValid = handInputValid(cardSelection);

                    if (isValid == false) {
                        System.out.println("Hand invalid, try again!");
                        cardSelection = scanString.nextLine().split(" ");
                    }
                }
                while (!isValid);

                //Sort selected card array index
                Arrays.sort(cardSelection);

                //Build the hand array
                ArrayList<Card> handSelected = new ArrayList<>();
                for (int i = 0; i < cardSelection.length; i++) {
                    handSelected.add(null);
                }
                for (int i = 0; i < cardSelection.length; i++) {
                    Card ret = gameInstance.getPlayer().getHand().getCardList().get(Integer.parseInt(cardSelection[i]) - 1);
                    handSelected.set(i, ret);
                }

                //Use actions: Play or Discard
                while (true) {
                    System.out.println("\nType \"Play\" to play hand, type \"Discard\" to discard hand.");
                    String action = scanString.nextLine();
                    if (action.equals("Play")) {

                        HandType currentHandType = Actions.HandTypeClassify(handSelected);

                        int currentChips = HandTypeChip(currentHandType);
                        int currentMult = HandTypeMult(currentHandType);

                        for (Card card : handSelected) currentChips += card.getRank().ordinal() + 2;

                        System.out.println("\n{~~~~~~~~~~~~~~~~~~~~~~~}");
                        System.out.println("Played " + Actions.HandTypeClassify(handSelected) + "!");
                        System.out.println("[" + currentChips + "] X [" + currentMult + "] = " + (currentChips * currentMult) + " chips");
                        System.out.println("{~~~~~~~~~~~~~~~~~~~~~~~}\n");

                        gameInstance.getPlayer().setScore(gameInstance.getPlayer().getScore() + (currentChips * currentMult));

                        playround--;
                        break;

                    } else if (action.equals("Discard")) {
                        if (playround == 0) {
                            System.out.println("No discards remaining!");
                        } else {
                            System.out.println("\n{~~~~~~~~~~~~}");
                            System.out.println("Discarded!");
                            System.out.println("{~~~~~~~~~~~~}\n");

                            discardround--;
                            break;
                        }

                    } else {
                        System.out.println("Invalid format!");
                    }
                }

                //Set the used cards isPlayed to True.
                for (int i = 0; i < cardSelection.length; i++) {
                    gameInstance.getPlayer().getHand().getCardList().get(Integer.parseInt(cardSelection[i]) - 1).setPlayed(true);
                }

                //Remove isPlayed cards
                for (int i = cardSelection.length - 1; i >= 0; i--) {
                    if (gameInstance.getPlayer().getHand().getCardList().get(Integer.parseInt(cardSelection[i]) - 1).isPlayed())
                        gameInstance.getPlayer().getHand().getCardList().remove(Integer.parseInt(cardSelection[i]) - 1);
                }

                //Fill hand
                gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());

                //End game if Playround remaining hit 0
                if (playround == 0)
                    break;

            }
            while (gameInstance.getPlayer().getScore() < gameInstance.getStage().getReqScore());

            //Scoring this round
            totalscore += gameInstance.getPlayer().getScore();

            if(gameInstance.getPlayer().getScore() >= gameInstance.getStage().getReqScore()){
                System.out.println("\n\nYOU WIN A ROUND!\n\n");
                gameInstance.getStage().setStageLv(gameInstance.getStage().getStageLv()+1);
                gameInstance.getPlayer().setScore(0);
            }
            else {
                isGameOver = true;
                System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("\n                   YOU LOSE!");
                System.out.println("\n       Your Total Score is " + totalscore );
                System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            }
        }

    }

    public static boolean handInputValid(String[] cardSelection)
    {
        boolean isValid = true;

        if(cardSelection.length == 0 || cardSelection.length > 5 || cardSelection[0].equals(""))
            isValid = false;

        for(int i = 0; i < cardSelection.length; i++)
        {
            for(int j = (i + 1); j < cardSelection.length; j++)
            {
                if(cardSelection[i].equals(cardSelection[j]))
                    isValid = false;
            }

            if(!(cardSelection[i].equals("1") || cardSelection[i].equals("2") || cardSelection[i].equals("3") ||  cardSelection[i].equals("4") || cardSelection[i].equals("5") ||  cardSelection[i].equals("6") ||  cardSelection[i].equals("7") ||  cardSelection[i].equals("8")))
                isValid = false;
        }

        return isValid;
    }

    public static int HandTypeMult(HandType handType)
    {
        int Mult = 0;

        if(handType.equals(HandType.RoyalFlush)) {
            Mult = 10;
        } else if(handType.equals(HandType.StraightFlush)) {
            Mult = 8;
        } else if(handType.equals(HandType.FourOfAKind)) {
            Mult = 7;
        } else if(handType.equals(HandType.FullHouse)) {
            Mult = 4;
        } else if(handType.equals(HandType.Flush)) {
            Mult = 4;
        } else if(handType.equals(HandType.Straight)) {
            Mult = 4;
        } else if(handType.equals(HandType.ThreeOfAKind)) {
            Mult = 3;
        } else if(handType.equals(HandType.TwoPair)) {
            Mult = 2;
        } else if(handType.equals(HandType.Pair)) {
            Mult = 2;
        } else {
            Mult = 1;
        }
        return Mult;
    }

    public static int HandTypeChip(HandType handType)
    {
        int Chip = 0;

        if(handType.equals(HandType.RoyalFlush)) {
            Chip = 100;
        } else if(handType.equals(HandType.StraightFlush)) {
            Chip = 80;
        } else if(handType.equals(HandType.FourOfAKind)) {
            Chip = 60;
        } else if(handType.equals(HandType.FullHouse)) {
            Chip = 50;
        } else if(handType.equals(HandType.Flush)) {
            Chip = 45;
        } else if(handType.equals(HandType.Straight)) {
            Chip = 40;
        } else if(handType.equals(HandType.ThreeOfAKind)) {
            Chip = 35;
        } else if(handType.equals(HandType.TwoPair)) {
            Chip = 30;
        } else if(handType.equals(HandType.Pair)) {
            Chip = 20;
        } else {
            Chip = 10;
        }
        return Chip;
    }



}
