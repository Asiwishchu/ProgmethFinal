 package logic.main;


import gui.*;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import utils.GameUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.card.Card;
import logic.game.Config;
import logic.game.GameController;

import java.util.ArrayList;

 public class Main extends Application {
    GameController gameInstance = GameController.getInstance();
    ArrayList<Card> cardSelection = gameInstance.getPlayer().getHand().getSelectedCards();

    StackPane stackPane = new StackPane();
    HBox root = new HBox(30);
    VBox alertSection = new VBox(10);

    SideBar mySideBar = new SideBar();
    EventScreen eventScreen = new EventScreen();

    CardDiv cardDiv = new CardDiv(this::initializeAlert);
    TarotDiv tarotDiv = new TarotDiv(this::initializeAlert);

    Media bgmSound = new Media(getClass().getResource("/Sound/song.mp3").toString());
    MediaPlayer bgmMediaPlayer = new MediaPlayer(bgmSound);

    public static void main(String[] args) {
        launch();
    }


    // Play Card
    public void playCard() {
        GameController gameInstance = GameController.getInstance();
        GameUtils.calculateScoreCard();

        int chips = gameInstance.getCurrentChips();
        int mult = gameInstance.getCurrentMult();

        for (Card card : cardSelection) {
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }

        System.out.println("Card Play : " + chips * mult);
        gameInstance.getPlayer().setScore(gameInstance.getPlayer().getScore() + (chips * mult));

        gameInstance.setMoney(gameInstance.getMoney() + gameInstance.getIncome());
        mySideBar.updateMoney();

        gameInstance.refillTarots();
        tarotDiv.updateTarotDiv();


        if (gameInstance.getHandSizeReset() == 0) {
            gameInstance.getPlayer().getHand().setHandSize(Config.DefaultHandSize);
            gameInstance.setHandSizeReset(Math.max(0, gameInstance.getHandSizeReset() - 1));
        }
        if (gameInstance.isTheTowerSetter()) {
            gameInstance.getBlind().setReqScore((gameInstance.getBlind().getReqScore() * 100) / 70);
        }

        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
        mySideBar.updatePlayerScore();
        gameInstance.setPlayHand(gameInstance.getPlayHand() - 1);
        cardSelection.clear();
        gameInstance.getPlayer().getHand().setSelectedCards(cardSelection);
        System.out.println("Play Function Score : " + gameInstance.getPlayer().getScore() + " Stage : " + gameInstance.getBlind().getReqScore());
        if (gameInstance.getPlayer().getScore() >= gameInstance.getBlind().getReqScore()) {
            gameInstance.getBlind().setBlindNo(gameInstance.getBlind().getBlindNo() + 1);
            gameInstance.getPlayer().setScore(0);
            mySideBar.updateRound();
            gameInstance.setPlayHand(gameInstance.getPlayer().getPlayRound());
            gameInstance.setMoney(gameInstance.getPlayer().getStartingMoney());
            gameInstance.setDiscard(gameInstance.getPlayer().getDiscardRound());
            mySideBar.updateSideBar();
            eventScreen.showWinningScreen(stackPane, root);

        }

        GameUtils.calculateScoreCard();
        mySideBar.updateHand();
        mySideBar.updateCardToPlay();

        if (gameInstance.getPlayHand() <= 0 && gameInstance.getPlayer().getScore() < gameInstance.getBlind().getReqScore()) {
            eventScreen.showLosingScreen(stackPane, root);
        }
    } // :playCard

    // Discard Card
    public void discardCard(ArrayList<Card> cardSelected) {
        for (Card card : cardSelection) {
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }
        GameController gameInstance = GameController.getInstance();
        gameInstance.setDiscard(gameInstance.getDiscard() - 1);
        cardSelected.clear();
        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
        cardDiv.updateCardDiv(mySideBar);
        eventScreen.showWinningScreen(stackPane, root);
    } // : discardCard

    // Initialize Alert message
    void initializeAlert(String message) {
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

        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.2), alertStackPane);
        slideIn.setFromX(100);
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
    }// : Initialize Alert message


    // Launch the Game
    public void start(Stage stage) {
        GameController gameInstance = GameController.getInstance();
        //init game
        gameInstance.initGameVar();

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


        // Play Zone =================
        VBox playZone = new VBox(0); // Adjust spacing as needed
        playZone.setAlignment(Pos.BOTTOM_CENTER);

        //Sound
        Media clickSound = new Media(getClass().getResource("/Sound/clickButton.mp3").toString());
        MediaPlayer clickMediaPlayer = new MediaPlayer(clickSound);

        // Button Zone ================
        HBox buttonZone = new HBox(50);
        buttonZone.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play Hand");
        playButton.setId("playButton"); // Set ID for play button
        playButton.setPadding(new Insets(5,10,5,10));
        playButton.setOnAction(e -> {
            if (!cardSelection.isEmpty()) {
                clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
                clickMediaPlayer.play();
                playCard();
                cardDiv.updateCardDiv(mySideBar);
            } else {
                initializeAlert("Please select card!");
            }
            clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
            clickMediaPlayer.play();
        });

        Button discardButton = new Button("Discard");
        discardButton.setId("discardButton"); // Set ID for discard button
        discardButton.setPadding(new Insets(5,10,5,10));
        discardButton.setOnAction(e -> {
            if (cardSelection.isEmpty()) {
                initializeAlert("at least 1 card");
            } else if (gameInstance.getDiscard() <= 0) {
                initializeAlert("out of discard!");
                return;
            }
            clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
            clickMediaPlayer.play();
            discardCard(cardSelection);
            cardDiv.updateCardDiv(mySideBar);
            mySideBar.updateDiscard();
        });
        // =============================


        // Zone Node ADDED
        buttonZone.getChildren().addAll(playButton, discardButton);
        playZone.getChildren().addAll(tarotDiv.initializeTarotDiv(),cardDiv.initializeCardDiv(mySideBar), buttonZone);
        root.getChildren().addAll(mySideBar.initializeSidebar(stackPane, root), playZone);


        bgmMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        bgmMediaPlayer.play();

        // Set stage properties
        Scene scene = new Scene(stackPane, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Arcana 888");
        stage.setResizable(false);
        Image Icon = new Image("etcPic/icon.png");
        stage.getIcons().add(Icon);
        stage.show();
    }
}
