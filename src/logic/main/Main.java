package logic.main;


import com.sun.util.reentrant.ReentrantContext;
import gui.EventScreen;
import gui.SideBar;
import gui.EventScreen;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import utils.GameUtils;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    StackPane stackPane = new StackPane();
    SideBar mySideBar = new SideBar();
    HBox root = new HBox(30);
    VBox alertSection = new VBox(10);
    EventScreen eventScreen = new EventScreen();

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

            cardImageView.setOnMouseEntered(e -> {
                cardImageView.setTranslateY(-5);
            });
            cardImageView.setOnMouseExited(e -> {
                cardImageView.setTranslateY(0);
            });

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
        cardDiv.setPrefHeight(50);
        cardDiv.setAlignment(Pos.CENTER);
        cardDiv.setPadding(new Insets(0, 30, 10, 0)); // Increase bottom padding to move cardDiv down
        cardDiv.setSpacing(-60);
        mySideBar.updateCardToPlay(0, 0, "Select Card");
    } // :updateCardDiv


    // Play Card
    public void playCard() {
        GameController gameInstance = GameController.getInstance();
        GameUtils.calculateScoreCard(cardSelection);

        if (gameInstance.getPlayHand() <= 0 && gameInstance.getPlayer().getScore() < gameInstance.getStage().getReqScore()) {
            eventScreen.showLosingScreen(stackPane, root);
        }

        int chips = gameInstance.getCurrentChips();
        int mult = gameInstance.getCurrentMult();

        for (Card card : cardSelection) {
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }

        System.out.println("Card Play : " + chips * mult);
        gameInstance.getPlayer().setScore(gameInstance.getPlayer().getScore() + (chips * mult));

        gameInstance.setMoney(gameInstance.getMoney() + gameInstance.getIncome());
        gameInstance.refillTarots();

        if (gameInstance.getHandSizeReset() == 0) {
            gameInstance.getPlayer().getHand().setHandSize(Config.DefaultHandSize);
            gameInstance.setHandSizeReset(Math.max(0, gameInstance.getHandSizeReset() - 1));
        }
        if (gameInstance.isTheTowerSetter()) {
            gameInstance.getStage().setReqScore((gameInstance.getStage().getReqScore() * 100) / 70);
        }

        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
        mySideBar.updatePlayerScore(gameInstance.getPlayer().getScore());
        gameInstance.setPlayHand(gameInstance.getPlayHand() - 1);
        cardSelection.clear();
        System.out.println("Play Function Score : " + gameInstance.getPlayer().getScore() + "Stage : " + gameInstance.getStage().getReqScore());
        if (gameInstance.getPlayer().getScore() >= gameInstance.getStage().getReqScore()) {
            gameInstance.getStage().setStageLv(gameInstance.getStage().getStageLv() + 1);
            gameInstance.getPlayer().setScore(0);
            mySideBar.updateRound(gameInstance.getStage().getStageLv());
            gameInstance.setPlayHand(gameInstance.getPlayer().getPlayRound());
            eventScreen.showWinningScreen(stackPane, root, gameInstance.getStage().getStageLv(), gameInstance.getMoney());
        }
        mySideBar.updateHand(gameInstance.getPlayHand(), gameInstance.getStage().getReqScore());
    } // :playCard


    // Discard Card
    public void discardCard(ArrayList<Card> cardSelected) {
        eventScreen.showPowerUpScreen(stackPane, root);
        for (Card card : cardSelection) {
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }
        GameController gameInstance = GameController.getInstance();
        gameInstance.setDiscard(gameInstance.getDiscard() - 1);
        cardSelected.clear();
        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
    } // : discardCard

    // Toast DIY
    public void initializeAlert(String message) {
        StackPane alertStackPane = new StackPane();
        Rectangle alertBox = new Rectangle(150, 40, Color.web("F9C91D"));
        alertBox.setStrokeWidth(2);
        alertBox.setStroke(Color.web("FFE791"));
        alertBox.setArcHeight(10);
        alertBox.setArcWidth(10);
        Text alertMessage = new Text(message);
        alertMessage.setId("alert-text");
        alertMessage.setFill(Color.WHITE);
        alertStackPane.getChildren().addAll(alertBox, alertMessage);

        alertSection.getChildren().add(alertStackPane);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(root, alertSection);

        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.3), alertStackPane);
        slideIn.setFromX(alertStackPane.getLayoutX());
        slideIn.setToX(0);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        slideIn.setOnFinished(event -> pause.play());
        pause.setOnFinished(event -> {
            alertSection.getChildren().remove(alertStackPane);
            // Fade out transition for a smooth disappearing effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0), alertSection);
            fadeOut.setFromValue(1); // Start opaque
            fadeOut.setToValue(0); // Finish transparent
            fadeOut.setOnFinished(e -> {
                alertSection.getChildren().remove(alertStackPane);
                stackPane.getChildren().remove(alertSection);
                stackPane.getChildren().add(alertSection);
            });
            fadeOut.play();
        });
        slideIn.play();
    }


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

        // alertSection
        alertSection.setPickOnBounds(false);
        alertSection.setPadding(new Insets(0, 0, 30, 820));
        alertSection.setAlignment(Pos.BOTTOM_RIGHT);
        alertSection.setPrefWidth(1000);
        alertSection.setPrefHeight(600);


        // Outer Box
        root.setPadding(new Insets(10, 0, 10, 10));
        root.setId("pane");
        stackPane.getChildren().addAll(alertSection, root);
        stackPane.setPickOnBounds(false);

        // tarot Description
        StackPane tarotDescriptionStackPane = new StackPane();
        Rectangle tarotDescriptionBox = new Rectangle(640, 140, Color.web("1E1E1E"));
        tarotDescriptionStackPane.setPadding(new Insets(0, 0,0,0));
        tarotDescriptionBox.setStroke(Color.web("3E4043"));
        tarotDescriptionBox.setStrokeWidth(3);
        tarotDescriptionBox.setArcHeight(10);
        tarotDescriptionBox.setArcWidth(10);
        Text tarotCardName = new Text("The Magician");
        tarotCardName.setId("tarot-card-name");
        Text tarotCardAbility = new Text("If you play Flush this hand Multiplier x2");
        tarotCardAbility.setId("tarot-card-ability");
        VBox tarotDescriptionVBox = new VBox(20);
        tarotDescriptionVBox.setPadding(new Insets(20,0,0,50));
        tarotDescriptionVBox.getChildren().addAll(tarotCardName, tarotCardAbility);
        tarotDescriptionStackPane.getChildren().addAll(tarotDescriptionBox, tarotDescriptionVBox);
        tarotDescriptionStackPane.getStylesheets().add(getClass().getResource("/tarotDescription.css").toExternalForm());

        // Play Zone =================
        VBox playZone = new VBox(0); // Adjust spacing as needed
        playZone.setAlignment(Pos.BOTTOM_CENTER);


        //tarot div
        HBox tarotDiv = new HBox();
        tarotDiv.setAlignment(Pos.CENTER);
        tarotDiv.setSpacing(20);


        Tarot[] tarots = GameController.createNewTarot(5);

        for (Tarot tarot : tarots) {
            ImageView tarotImage = new ImageView(tarot.getTarotImage());
            tarotImage.setFitHeight(190);
            tarotImage.setFitWidth(120);
            tarotDiv.getChildren().add(tarotImage);

            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), tarotImage);
            scaleIn.setToX(1.07);
            scaleIn.setToY(1.07);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), tarotImage);
            scaleOut.setToX(1);
            scaleOut.setToY(1);

            AtomicBoolean isScaled = new AtomicBoolean(false); // Flag to track if card is scaled

            tarotImage.setOnMouseClicked(e -> {
                if (isScaled.get()) {
                    scaleOut.play();
                    isScaled.set(false);

                } else {
                    scaleIn.play();
                    isScaled.set(true);
                }
            });

        }

        tarotDiv.setPrefWidth(600);
        tarotDiv.setPrefHeight(250);
        tarotDiv.setPadding(new Insets(0, 30, 0, 0));

        gameInstance.refillTarots();
        gameInstance.setSelectedTarots(new ArrayList<>());


        HBox cardDiv = new HBox();
        cardDiv.setAlignment(Pos.CENTER);
        cardDiv.setPadding(new Insets(0, 30, 10, 0));
        cardDiv.setSpacing(-60);
        cardDiv.setPrefWidth(100);
        cardDiv.setPrefHeight(50);
        // ============================


        // Button Zone ================
        HBox buttonZone = new HBox(50);
        buttonZone.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play Hand");
        playButton.setId("playButton"); // Set ID for play button
        playButton.setOnAction(e -> {
            if (!cardSelection.isEmpty()) {
                playCard();
                updateCardDiv(cardDiv, gameInstance.getPlayer().getHand().getCardList());
            } else {
                initializeAlert("Please select card!");
            }
            playCard();
            updateCardDiv(cardDiv, gameInstance.getPlayer().getHand().getCardList());
        });

        Button discardButton = new Button("Discard");
        discardButton.setId("discardButton"); // Set ID for discard button
        discardButton.setOnAction(e -> {
            if (cardSelection.isEmpty()) {
                initializeAlert("at least 1 card");
            } else if (gameInstance.getDiscard() <= 0) {
                initializeAlert("out of discard!");
                return;
            }
            discardCard(cardSelection);
            updateCardDiv(cardDiv, gameInstance.getPlayer().getHand().getCardList());
            mySideBar.updateDiscard(gameInstance.getDiscard());
        });
        // =============================


        // Zone Node ADDED
        buttonZone.getChildren().addAll(playButton, discardButton);
        playZone.getChildren().addAll(tarotDescriptionStackPane,tarotDiv,cardDiv, buttonZone);
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