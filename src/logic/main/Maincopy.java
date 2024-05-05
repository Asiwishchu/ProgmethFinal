package logic.main;

import logic.card.Card;
import logic.game.CardClassifier;
import logic.game.Config;
import logic.game.GameController;
import logic.tarot.Tarot;
import utils.GameUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class Maincopy {
    public static void main(String[] args) {
        launch();
        GameController gameInstance = GameController.getInstance();

        boolean isGameOver = false;
        int totalscore = 0;

        while (!isGameOver) {

            //Initialize round
            gameInstance.getPlayer().getHand().initHand();
            gameInstance.initAndShuffleDeck();
            gameInstance.setPlayHand(gameInstance.getPlayer().getPlayRound());
            gameInstance.setDiscard(gameInstance.getPlayer().getDiscardRound());
            gameInstance.setMoney(gameInstance.getPlayer().getStartingMoney());
            gameInstance.setIncome(gameInstance.getPlayer().getStartingIncome());
            gameInstance.refillTarots();
            gameInstance.setSelectedTarots(new ArrayList<>());

            //Fill first hand
            gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());

            do {
                //Stage Start
                System.out.println("|---------------------------------------------------------------|");
                System.out.println(gameInstance.getBlind().getBlindNo() + " Stage: Score at least " + gameInstance.getBlind().getReqScore());
                System.out.println("Round score: " + gameInstance.getPlayer().getScore());
                System.out.println("\n{Hands: " + gameInstance.getPlayHand() + "}   {Discards: " + gameInstance.getDiscard() + "}   {Money: " + gameInstance.getMoney() + "}");
                System.out.println("|---------------------------------------------------------------|");


                //Display current Tarot
                System.out.println("\nCurrent Tarot: ");
                for (int i = 0; i < gameInstance.getTarotArrayList().size(); i++) {
                    System.out.print("[" + gameInstance.getTarotArrayList().get(i).getName() + "] ");
                }

                //Display current Hand
                System.out.println("\nCurrent Hand: ");
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
                    isValid = GameUtils.handInputValid(cardSelection);

                    if (!isValid) {
                        System.out.println("Hand invalid, try again!");
                        cardSelection = scanString.nextLine().split(" ");
                    }
                } while (!isValid);

                Arrays.sort(cardSelection);  //Sort selected card array index

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

                    //PLAY HAND
                    if (action.equals("Play")) {

                        gameInstance.setCurrentHandType(CardClassifier.HandTypeClassify(handSelected));

                        gameInstance.setCurrentChips(GameUtils.HandTypeChip(gameInstance.getCurrentHandType()));
                        gameInstance.setCurrentMult(GameUtils.HandTypeMult(gameInstance.getCurrentHandType()));

                        for (Card card : handSelected)
                            gameInstance.setCurrentChips(gameInstance.getCurrentChips() + (card.getRank().ordinal() + 2));

                        //Tarot's ability activating
                        if (!GameController.getInstance().getSelectedTarots().isEmpty()) {
                            for (Tarot tarot : GameController.getInstance().getSelectedTarots()) {
                                tarot.useAbility();
                            }
                        }
                        gameInstance.setSelectedTarots(new ArrayList<>());

                        int chips = gameInstance.getCurrentChips();
                        int mult = gameInstance.getCurrentMult();

                        System.out.println("\n{~~~~~~~~~~~~~~~~~~~~~~~}");
                        System.out.println("Played " + gameInstance.getCurrentHandType() + "!");
                        System.out.println("[" + chips + "] X [" + mult + "] = " + (chips * mult) + " chips");
                        System.out.println("{~~~~~~~~~~~~~~~~~~~~~~~}\n");

                        gameInstance.getPlayer().setScore(gameInstance.getPlayer().getScore() + (chips * mult));          //Scoring Played Hand

                        gameInstance.setMoney(gameInstance.getMoney() + gameInstance.getIncome());                         //add money = income
                        gameInstance.refillTarots();                                                                       //refill tarots
                        if (gameInstance.getHandSizeReset() == 0)
                            gameInstance.getPlayer().getHand().setHandSize(Config.DefaultHandSize);                        //reset hand size
                        gameInstance.setHandSizeReset(Math.max(0, gameInstance.getHandSizeReset() - 1));                   //hand size setter
                        if (gameInstance.isTheTowerSetter()) {                                                             //if the tower is played
                            gameInstance.getBlind().setReqScore((gameInstance.getBlind().getReqScore() * 100) / 70);
                        }

                        gameInstance.setPlayHand(gameInstance.getPlayHand() - 1);
                        break;
                    }

                    //DISCARD
                    else if (action.equals("Discard")) {
                        if (gameInstance.getDiscard() == 0) {
                            System.out.println("No discards remaining!");
                        } else {
                            System.out.println("\n{~~~~~~~~~~~~}");
                            System.out.println("Discarded!");
                            System.out.println("{~~~~~~~~~~~~}\n");

                            gameInstance.setDiscard(gameInstance.getDiscard() - 1);
                            break;
                        }
                    }

                    //Invalid input
                    else {
                        System.out.println("Invalid format!");
                    }
                }

                //Set the used cards isPlayed to True.
                for (String string : cardSelection) {
                    gameInstance.getPlayer().getHand().getCardList().get(Integer.parseInt(string) - 1).setPlayed(true);
                }

                //Remove isPlayed cards
                for (int i = cardSelection.length - 1; i >= 0; i--) {
                    if (gameInstance.getPlayer().getHand().getCardList().get(Integer.parseInt(cardSelection[i]) - 1).isPlayed())
                        gameInstance.getPlayer().getHand().getCardList().remove(Integer.parseInt(cardSelection[i]) - 1);
                }

                //Fill hand
                gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());

                //End game if Playable Hand remaining hit 0
                if (gameInstance.getPlayHand() == 0) break;

            } while (gameInstance.getPlayer().getScore() < gameInstance.getBlind().getReqScore());

            //Blind end with win
            if (gameInstance.getPlayer().getScore() >= gameInstance.getBlind().getReqScore()) {
                System.out.println("\n\nYOU WIN A ROUND!\n\n");
                totalscore += gameInstance.getBlind().getReqScore();
                gameInstance.getBlind().setBlindNo(gameInstance.getBlind().getBlindNo() + 1);
                gameInstance.getPlayer().setScore(0);

                //TODO player choosing reward
                // permenantly +1 Hands OR +1 Discards OR +2 StartingMoney OR +1 StartingIncome
//                rewardHand.setOnAction(e -> {
//                    GameController.getInstance().getPlayer().setPlayRound(GameController.getInstance().getPlayer().getPlayRound() + 1);
//                    clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
//                    clickMediaPlayer.play();
//                });
//                rewardDiscard.setOnAction(e -> {
//                    GameController.getInstance().getPlayer().setDiscardRound(GameController.getInstance().getPlayer().getDiscardRound() + 1);
//                    clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
//                    clickMediaPlayer.play();
//                });
//                rewardMoney.setOnAction(e -> {
//                    GameController.getInstance().getPlayer().setStartingMoney(GameController.getInstance().getPlayer().getStartingMoney() + 3);
//                    clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
//                    clickMediaPlayer.play();
//                });

            }
            //Blind end with lose
            else {
                isGameOver = true;
                totalscore += gameInstance.getPlayer().getScore();
                System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("\n                   YOU LOSE!");
                System.out.println("\n       Your Total Score is " + totalscore);
                System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            }
        }

    }
}