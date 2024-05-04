package logic.main;


import gui.SideBar;
import utils.GameUtils;
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
    GameController gameInstance = GameController.getInstance();
    ArrayList<Card> cardSelection = gameInstance.getPlayer().getHand().getSelectedCards();
    SideBar mySideBar = new SideBar();

    public static void main(String[] args) {
        launch();
    }

    // ==============================
    // User Interface Working Section ===================================================================
    // ==============================


    // Card Rendering Function
    public void updateCardDiv(HBox cardDiv, ArrayList<Card> updatedHandList) {
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
                if (cardSelection.isEmpty()) {
                    mySideBar.updateCardToPlay(0, 0, "Select Card");
                    return;
                }
                String handType = GameUtils.calculateScoreCard(cardSelection);
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
    public void playCard() {
        GameController gameInstance = GameController.getInstance();
        GameUtils.calculateScoreCard(cardSelection);

        int chips = gameInstance.getCurrentChips();
        int mult = gameInstance.getCurrentMult();

        for (Card card : cardSelection) {
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }

        System.out.println("Card Play : " + chips * mult);
        gameInstance.getPlayer().setScore(gameInstance.getPlayer().getScore() + (chips * mult));

        gameInstance.setMoney(gameInstance.getMoney() + gameInstance.getIncome());
        gameInstance.refillTarots();

        if (gameInstance.getHandSizeReset() == 0){
            gameInstance.getPlayer().getHand().setHandSize(Config.DefaultHandSize);
            gameInstance.setHandSizeReset(Math.max(0, gameInstance.getHandSizeReset() - 1));
        }
        if (gameInstance.isTheTowerSetter()) {
            gameInstance.getStage().setReqScore((gameInstance.getStage().getReqScore() * 100) / 70);
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
        if(cardSelected.isEmpty()){
            return;
        }
        for(Card card : cardSelection){
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }
        GameController gameInstance = GameController.getInstance();
        gameInstance.setDiscard(gameInstance.getDiscard() - 1);
        cardSelected.clear();
        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
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

        Button playButton = new Button("Play Hand");
        playButton.setId("playButton"); // Set ID for play button
        playButton.setOnAction(e -> {
            if(cardSelection.isEmpty()) {
                System.out.println("please select card to play");
                // TODO pop up message "please select card to play"
                return;
            }
            playCard();
            updateCardDiv(cardDiv, gameInstance.getPlayer().getHand().getCardList());
        });

        Button discardButton = new Button("Discard");
        discardButton.setId("discardButton"); // Set ID for discard button
        discardButton.setOnAction(e -> {
            if(gameInstance.getDiscard() <= 0 || cardSelection.isEmpty()) return;
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