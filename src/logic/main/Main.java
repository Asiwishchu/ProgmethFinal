package logic.main;


import gui.SideBar;
import utils.util;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.card.Card;
import logic.game.Actions;
import logic.game.Config;
import logic.game.GameController;
import logic.tarot.Tarot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {


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
                    isValid = util.handInputValid(cardSelection);

                    if (isValid == false) {
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

                        gameInstance.setCurrentHandType(Actions.HandTypeClassify(handSelected));

                        gameInstance.setCurrentChips(util.HandTypeChip(gameInstance.getCurrentHandType()));
                        gameInstance.setCurrentMult(util.HandTypeMult(gameInstance.getCurrentHandType()));

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

                        gameInstance.setMoney(gameInstance.getMoney() + gameInstance.getIncome());                                      //add money = income
                        gameInstance.refillTarots();                                                                                    //refill tarots
                        if (gameInstance.getHandSizeReset() == 0)
                            gameInstance.getPlayer().getHand().setHandSize(Config.DefaultHandSize);//reset hand size
                        gameInstance.setHandSizeReset(Math.max(0, gameInstance.getHandSizeReset() - 1));                                  //hand size setter
                        if (gameInstance.isTheTowerSetter()) {                                                                           //if the tower is played
                            gameInstance.getStage().setReqScore((gameInstance.getStage().getReqScore() * 100) / 70);
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
                if (gameInstance.getPlayHand() == 0) break;

            } while (gameInstance.getPlayer().getScore() < gameInstance.getStage().getReqScore());

            //Scoring this blind
            totalscore += gameInstance.getPlayer().getScore();

            //Blind end with win
            if (gameInstance.getPlayer().getScore() >= gameInstance.getStage().getReqScore()) {
                System.out.println("\n\nYOU WIN A ROUND!\n\n");
                gameInstance.getStage().setStageLv(gameInstance.getStage().getStageLv() + 1);
                gameInstance.getPlayer().setScore(0);

                //TODO player choosing reward
                // permenantly +1 Hands OR +1 Discards OR +2 StartingMoney OR +1 StartingIncome
            }
            //Blind end with lose
            else {
                isGameOver = true;
                System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                System.out.println("\n                   YOU LOSE!");
                System.out.println("\n       Your Total Score is " + totalscore);
                System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            }
        }

    }

    // ==============================
    // User Interface Working Section ===================================================================
    // ==============================

    ArrayList<Card> cardSelection = new ArrayList<>();
    SideBar mySideBar = new SideBar();


    // Card Rendering Function
    public void updateCardDiv(HBox cardDiv, ArrayList<Card> updatedHandList) {
        GameController gameInstance = GameController.getInstance();
        cardDiv.getChildren().clear();

        for (Card card : updatedHandList) {
            ImageView cardImageView = new ImageView(card.getCardImage());

            // Hover effect
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), cardImageView);
            scaleIn.setToX(1.2);
            scaleIn.setToY(1.2);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), cardImageView);
            scaleOut.setToX(1);
            scaleOut.setToY(1);

            AtomicBoolean isScaled = new AtomicBoolean(false); // Flag to track if card is scaled

            cardImageView.setOnMouseClicked(e -> {
                if (isScaled.get()) {
                    scaleOut.play();
                    isScaled.set(false);
                    cardSelection.remove(card);
                } else {
                    scaleIn.play();
                    isScaled.set(true);
                    cardSelection.add(card);
                }
                if (cardSelection.size() <= 0) {
                    mySideBar.updateCardToPlay(0, 0, "Select Card");
                    return;
                }
                String handType = util.calculateScoreCard(cardSelection);
                int chip = gameInstance.getCurrentChips();
                int multiplier = gameInstance.getCurrentMult();
                mySideBar.updateCardToPlay(chip, multiplier, handType);
            });
            cardImageView.setFitWidth(140);
            cardImageView.setFitHeight(140);
            cardDiv.getChildren().add(cardImageView);
        }
        cardDiv.setPrefWidth(680);
        cardDiv.setPrefHeight(200);
        cardDiv.setAlignment(Pos.CENTER);
        cardDiv.setPadding(new Insets(250, 30, 20, 0)); // Increase bottom padding to move cardDiv down
        cardDiv.setSpacing(-60);
        mySideBar.updateCardToPlay(0, 0, "Select Card");
    } // :updateCardDiv


    // Play Card
    public void playCard(ArrayList<Card> cardSelected) {
        GameController gameInstance = GameController.getInstance();
        util.calculateScoreCard(cardSelected);

        int chips = gameInstance.getCurrentChips();
        int mult = gameInstance.getCurrentMult();

        System.out.println("Card Play : " + chips * mult);
        gameInstance.getPlayer().setScore(gameInstance.getPlayer().getScore() + (chips * mult));

        for (Card card : cardSelection) {
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }

        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
        mySideBar.updatePlayerScore(gameInstance.getPlayer().getScore());
        gameInstance.setPlayHand(gameInstance.getPlayHand() - 1);
        mySideBar.updateHand(gameInstance.getPlayHand());
        cardSelection.clear();
        System.out.println("Play Function Score : " + gameInstance.getPlayer().getScore() + "Stage : " + gameInstance.getStage().getReqScore());
        if (gameInstance.getPlayer().getScore() >= gameInstance.getStage().getReqScore()){
            gameInstance.getStage().setStageLv(gameInstance.getStage().getStageLv() + 1);
            gameInstance.getPlayer().setScore(0);
            mySideBar.updateRound(gameInstance.getStage().getStageLv());
            gameInstance.setPlayHand(gameInstance.getPlayer().getPlayRound());
        }
        mySideBar.updateHand(gameInstance.getPlayHand());
    } // :playCard


    // Discard Card
    public void discardCard(ArrayList<Card> cardSelected) {
        GameController gameInstance = GameController.getInstance();
        for (Card card : cardSelection) {
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }
        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
        cardSelection.clear();
    } // : discardCard


    // Launch the Game
    public void start(Stage stage) {

        GameController gameInstance = GameController.getInstance();

        //Initialize round
        gameInstance.getPlayer().getHand().initHand();
        gameInstance.initAndShuffleDeck();
        gameInstance.setPlayHand(gameInstance.getPlayer().getPlayRound());
        gameInstance.setDiscard(gameInstance.getPlayer().getDiscardRound());
        gameInstance.setMoney(gameInstance.getPlayer().getStartingMoney());
        gameInstance.setIncome(gameInstance.getPlayer().getStartingIncome());
        gameInstance.refillTarots();
        gameInstance.setSelectedTarots(new ArrayList<>());
        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());

        // Outer Box
        HBox root = new HBox();
        root.setPadding(new Insets(10, 0, 10, 10));
        root.setId("pane");
        StackPane stackPane = new StackPane(root);

        // Play Zone =================
        VBox playZone = new VBox(20); // Adjust spacing as needed
        playZone.setAlignment(Pos.CENTER);
        playZone.setPadding(new Insets(20));

        HBox cardDiv = new HBox();
        cardDiv.setAlignment(Pos.CENTER);
        cardDiv.setPadding(new Insets(250, 30, 20, 0)); // Increase bottom padding to move cardDiv down
        cardDiv.setSpacing(-60);
        cardDiv.setPrefWidth(680);
        cardDiv.setPrefHeight(200);
        // ============================


        // Button Zone ================
        HBox buttonZone = new HBox(50);
        buttonZone.setAlignment(Pos.CENTER);
        buttonZone.setPadding(new Insets(0, 0, 20, 0)); // Increase top padding to move buttonZone down

        Button playButton = new Button("Play");
        playButton.setId("playButton"); // Set ID for play button
        playButton.setOnAction(e -> {
            playCard(cardSelection);
            updateCardDiv(cardDiv, gameInstance.getPlayer().getHand().getCardList());
        });

        Button discardButton = new Button("Discard");
        discardButton.setId("discardButton"); // Set ID for discard button
        discardButton.setOnAction(e -> {
            System.out.println(gameInstance.getDiscard() + " || " + cardSelection.size());
            if(gameInstance.getDiscard() <= 0 || gameInstance.getDiscard() < cardSelection.size()){
                return;
            }
            gameInstance.setDiscard(gameInstance.getDiscard() - cardSelection.size());
            discardCard(cardSelection);
            updateCardDiv(cardDiv, gameInstance.getPlayer().getHand().getCardList());
            mySideBar.updateDiscard(gameInstance.getDiscard());
        });
        // =============================


        // Zone Node ADDED
        buttonZone.getChildren().addAll(playButton, discardButton);
        playZone.getChildren().addAll(cardDiv, buttonZone);
        root.getChildren().addAll(mySideBar.initializeSidebar(stackPane, root), playZone);


        // Render Hand Card list ===========
        updateCardDiv(cardDiv, gameInstance.getPlayer().getHand().getCardList());
        //=================================


        // Set stage properties
        Scene scene = new Scene(stackPane, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Better Balatro");
        stage.setResizable(false);
        Image betterBalatroIcon = new Image("BetterBalatro.jpeg");
        stage.getIcons().add(betterBalatroIcon);
        stage.show();
    }
}