package logic.main;


//import javafx.application.Application;
//import javafx.stage.Stage;

import application.HandType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.card.Card;
import logic.game.Actions;
import logic.game.Config;
import logic.game.GameController;
import logic.tarot.Tarot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//public class Main extends Application{
//    @Override
//    public void start(Stage stage) throws Exception {
//
//    }
public class Main extends Application {

    public static void main(String[] args) {
//        launch();
        GameController gameInstance = GameController.getInstance();

        boolean isGameOver = false;
        int totalscore = 0;

        while(!isGameOver) {

            //Initialize round
            gameInstance.getPlayer().getHand().initHand();
            gameInstance.initAndShuffleDeck();
            gameInstance.setPlayHand(gameInstance.getPlayer().getPlayRound());
            gameInstance.setDiscard(gameInstance.getPlayer().getDiscardRound());
            gameInstance.setMoney(gameInstance.getPlayer().getStartingMoney());
            gameInstance.setIncome(gameInstance.getPlayer().getStartingIncome());
            gameInstance.refillTarots();

            //Fill first hand
            gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());

            do {
                //Stage Start
                System.out.println("|---------------------------------------------------------------|");
                System.out.println(gameInstance.getStage().getStageLv() + " Stage: Score at least " + gameInstance.getStage().getReqScore());
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
                    isValid = handInputValid(cardSelection);

                    if (isValid == false) {
                        System.out.println("Hand invalid, try again!");
                        cardSelection = scanString.nextLine().split(" ");
                    }
                }
                while (!isValid);

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

                        HandType currentHandType = Actions.HandTypeClassify(handSelected);

                        gameInstance.setCurrentChips(HandTypeChip(currentHandType));
                        gameInstance.setCurrentMult(HandTypeMult(currentHandType));

                        for (Card card : handSelected) gameInstance.setCurrentChips( gameInstance.getCurrentChips() + (card.getRank().ordinal() + 2));

                        //Tarot's ability activating
                        if(!GameController.getInstance().getTarotArrayList().isEmpty()) {
                            for (Tarot tarot : GameController.getInstance().getTarotArrayList()) {
                                tarot.useAbility();
                            }
                        }

                        int chips = gameInstance.getCurrentChips();
                        int mult = gameInstance.getCurrentMult();

                        System.out.println("\n{~~~~~~~~~~~~~~~~~~~~~~~}");
                        System.out.println("Played " + Actions.HandTypeClassify(handSelected) + "!");
                        System.out.println("[" + chips + "] X [" + mult + "] = " + (chips * mult) + " chips");
                        System.out.println("{~~~~~~~~~~~~~~~~~~~~~~~}\n");

                        gameInstance.getPlayer().setScore(gameInstance.getPlayer().getScore() + (chips * mult));          //Scoring Played Hand

                        gameInstance.setMoney(gameInstance.getMoney() + gameInstance.getIncome());                                      //add money = income
                        gameInstance.refillTarots();                                                                                    //refill tarots
                        if(gameInstance.getHandSizeReset() == 0) gameInstance.getPlayer().getHand().setHandSize(Config.DefaultHandSize);//reset hand size
                        gameInstance.setHandSizeReset(Math.max(0, gameInstance.getHandSizeReset()-1));                                  //hand size setter

                        gameInstance.setPlayHand(gameInstance.getPlayHand()-1);
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

                            gameInstance.setDiscard(gameInstance.getDiscard()-1);
                            break;
                        }

                    }

                    //Invalid input
                    else {
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

                //End game if Playable Hand remaining hit 0
                if (gameInstance.getPlayHand() == 0)
                    break;

            }
            while (gameInstance.getPlayer().getScore() < gameInstance.getStage().getReqScore());

            //Scoring this blind
            totalscore += gameInstance.getPlayer().getScore();

            //Blind end with win
            if(gameInstance.getPlayer().getScore() >= gameInstance.getStage().getReqScore()){
                System.out.println("\n\nYOU WIN A ROUND!\n\n");
                gameInstance.getStage().setStageLv(gameInstance.getStage().getStageLv()+1);
                gameInstance.getPlayer().setScore(0);

                //TODO player choosing reward
                // permenantly +1 Hands OR +1 Discards OR +2 StartingMoney OR +1 StartingIncome
            }
            //Blind end with lose
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

        if(cardSelection.length == 0 || cardSelection.length > 5 || cardSelection[0].isEmpty())
            isValid = false;

        for(int i = 0; i < cardSelection.length; i++)
        {
            for(int j = (i + 1); j < cardSelection.length; j++)
            {
                if (cardSelection[i].equals(cardSelection[j])) {
                    isValid = false;
                    break;
                }
            }

            if(!(cardSelection[i].equals("1") || cardSelection[i].equals("2") || cardSelection[i].equals("3") ||  cardSelection[i].equals("4") || cardSelection[i].equals("5") ||  cardSelection[i].equals("6") ||  cardSelection[i].equals("7") ||  cardSelection[i].equals("8") ||  cardSelection[i].equals("9")))
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


    public void start(Stage stage){

        VBox root = new VBox();
        Scene scene = new Scene(root,1000,600);

        stage.setTitle("Pocker Card Game");
        stage.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.show();


    }
    }
