 package logic.main;


import application.AlertHandler;
import gui.*;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.game.Alert;
import javafx.stage.Screen;
import logic.tarot.Tarot;
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

    SideBar mySideBar = new SideBar();
    EventScreen eventScreen = new EventScreen();

    Alert alert = gameInstance.getAlert();

    StackPane stackPane = alert.getStackPane();
    HBox root = alert.getRoot();
    VBox alertSection = alert.getAlertSection();

    CardDiv cardDiv = new CardDiv();
    TarotDiv tarotDiv = new TarotDiv();

    Media bgmSound = new Media(getClass().getResource("/Sound/song.mp3").toString());
    MediaPlayer bgmMediaPlayer = new MediaPlayer(bgmSound);

    public static void main(String[] args) {
        launch();
    }


    // Play Card
    public void playCard() {
        GameController gameInstance = GameController.getInstance();
        GameUtils.calculateScoreCard();

        //Tarot's ability activating
        if (!GameController.getInstance().getSelectedTarots().isEmpty()) {
            for (Tarot tarot : GameController.getInstance().getSelectedTarots()) {
                tarot.useAbility();
            }
        }
        gameInstance.getSelectedTarots().clear();
        gameInstance.refillTarots();
        tarotDiv.updateTarotDiv(mySideBar);
        mySideBar.updateGoal();

        int chips = gameInstance.getCurrentChips();
        int mult = gameInstance.getCurrentMult();

        for (Card card : gameInstance.getPlayer().getHand().getSelectedCards()) {
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }

        System.out.println("Card Play : " + chips * mult);
        gameInstance.getPlayer().setScore(gameInstance.getPlayer().getScore() + (chips * mult));

        gameInstance.setMoney(gameInstance.getMoney() + gameInstance.getIncome());
        initializeAlert("Get $ " + gameInstance.getIncome(), Config.BLUE);
        initializeAlert("+ " + (chips * mult) + " score", Config.BLUE);
        mySideBar.updateMoney();

        if (gameInstance.getHandSizeReset() == 0) {
            gameInstance.getPlayer().getHand().setHandSize(Config.DefaultHandSize);
            gameInstance.setHandSizeReset(Math.max(0, gameInstance.getHandSizeReset() - 1));
        }

        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
        mySideBar.updatePlayerScore();
        gameInstance.setPlayHand(gameInstance.getPlayHand() - 1);
        gameInstance.getPlayer().getHand().getSelectedCards().clear();
        gameInstance.getPlayer().getHand().setSelectedCards(gameInstance.getPlayer().getHand().getSelectedCards());
        System.out.println("Play Function Score : " + gameInstance.getPlayer().getScore() + " Stage : " + gameInstance.getBlind().getReqScore());

        if (gameInstance.getPlayer().getScore() >= gameInstance.getBlind().getReqScore()) {
            gameInstance.getBlind().setBlindNo(gameInstance.getBlind().getBlindNo() + 1);
            gameInstance.getBlind().initReqScore();
            gameInstance.getPlayer().setScore(0);
            gameInstance.setTheTowerSetter(false);
            mySideBar.updateRound();
            gameInstance.setPlayHand(gameInstance.getPlayer().getPlayRound());
            gameInstance.setMoney(gameInstance.getPlayer().getStartingMoney());
            gameInstance.setDiscard(gameInstance.getPlayer().getDiscardRound());
            mySideBar.updateSideBar();
            eventScreen.showWinningScreen(stackPane, root, mySideBar);
            gameInstance.setMoney(gameInstance.getPlayer().getStartingMoney());
            gameInstance.setDiscard(gameInstance.getPlayer().getDiscardRound());
            gameInstance.setPlayHand(gameInstance.getPlayer().getPlayRound());
            gameInstance.setIncome(gameInstance.getPlayer().getStartingIncome());
        }

        GameUtils.calculateScoreCard();
        mySideBar.updateSideBar();

        if (gameInstance.getPlayHand() <= 0 && gameInstance.getPlayer().getScore() < gameInstance.getBlind().getReqScore()) {
            eventScreen.showLosingScreen(stackPane, root, mySideBar);
            mySideBar.updateSideBar();
        }
    } // :playCard

    // Discard Card
    public void discardCard(ArrayList<Card> cardSelected) {
        for (Card card : gameInstance.getPlayer().getHand().getSelectedCards()) {
            gameInstance.getPlayer().getHand().getCardList().remove(card);
        }
        GameController gameInstance = GameController.getInstance();
        gameInstance.setDiscard(gameInstance.getDiscard() - 1);
        cardSelected.clear();
        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
        cardDiv.updateCardDiv(mySideBar);
    } // : discardCard

    // Initialize Alert message
    public void initializeAlert(String message, String color) {
        StackPane alertStackPane = new StackPane();
        Rectangle alertBox = new Rectangle(150, 40, Color.web(color));
        alertBox.setStrokeWidth(2);
        alertBox.setStroke(Color.SNOW);
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
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        //init game
        gameInstance.initGameVar();

        // alertSection
        alertSection.setPickOnBounds(false);
        alertSection.setPadding(new Insets(0, 0, 30, screenBounds.getWidth()-200));
        alertSection.setAlignment(Pos.BOTTOM_RIGHT);
        alertSection.setPrefWidth(screenBounds.getWidth());
        alertSection.setPrefHeight(screenBounds.getHeight());

        // Outer Box
        root.setPadding(new Insets(10, 0, 10, 10));
        root.setId("pane");
        stackPane.getChildren().addAll(alertSection, root);
        stackPane.setPickOnBounds(false);


        // Play Zone =================
        VBox playZone = new VBox(0); // Adjust spacing as needed
        playZone.setAlignment(Pos.BOTTOM_CENTER);
        playZone.setPrefWidth(screenBounds.getWidth()-240);

        //Sound
        Media clickSound = new Media(getClass().getResource("/Sound/clickButton.mp3").toString());
        MediaPlayer clickMediaPlayer = new MediaPlayer(clickSound);

        // Button Zone ================
        HBox buttonZone = new HBox(50);
        buttonZone.setAlignment(Pos.CENTER);
        buttonZone.setPadding(new Insets(0,0,40,0));

        Button playButton = new Button("Play Hand");
        playButton.setId("playButton"); // Set ID for play button
        playButton.setPadding(new Insets(20,40,20,40));
        playButton.setOnAction(e -> {
            clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
            clickMediaPlayer.play();
            if (gameInstance.getPlayer().getHand().getSelectedCards().isEmpty()) {
                initializeAlert("Please select card!", Config.YELLLOW);
            } else {
                playCard();
                cardDiv.updateCardDiv(mySideBar);
                mySideBar.updateRound();
            }
        });

        Button discardButton = new Button("Discard");
        discardButton.setId("discardButton"); // Set ID for discard button
        discardButton.setPadding(new Insets(20,40,20,40));
        discardButton.setOnAction(e -> {
            clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
            clickMediaPlayer.play();
            if (gameInstance.getPlayer().getHand().getSelectedCards().isEmpty()) {
                initializeAlert("at least 1 card", Config.YELLLOW);
            } else if (gameInstance.getDiscard() <= 0) {
                initializeAlert("out of discard!", Config.YELLLOW);
                return;
            }
            discardCard(gameInstance.getPlayer().getHand().getSelectedCards());
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
        Scene scene = new Scene(stackPane, screenBounds.getWidth(),screenBounds.getHeight());
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());;
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.setTitle("Arcana 888");
        stage.setResizable(false);
        Image Icon = new Image("etcPic/icon.png");
        stage.getIcons().add(Icon);
        stage.show();
    }
}
