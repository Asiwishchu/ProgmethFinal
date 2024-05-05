 package logic.main;


import gui.*;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
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

    SideBar mySideBar = new SideBar();
    EventScreen eventScreen = new EventScreen();

    Alert alert = GameController.getInstance().getAlert();

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
        GameUtils.calculateScoreCard();

        //Tarot's ability activating
        if (!GameController.getInstance().getSelectedTarots().isEmpty()) {
            for (Tarot tarot : GameController.getInstance().getSelectedTarots()) {
                tarot.useAbility();
            }
        }
        GameController.getInstance().getSelectedTarots().clear();
        GameController.getInstance().refillTarots();
        tarotDiv.updateTarotDiv(mySideBar);
        mySideBar.updateGoal();

        int chips = GameController.getInstance().getCurrentChips();
        int mult = GameController.getInstance().getCurrentMult();

        for (Card card : GameController.getInstance().getPlayer().getHand().getSelectedCards()) {
            GameController.getInstance().getPlayer().getHand().getCardList().remove(card);
        }

        System.out.println("Card Play : " + chips * mult);
        GameController.getInstance().getPlayer().setScore(GameController.getInstance().getPlayer().getScore() + (chips * mult));

        GameController.getInstance().setMoney(GameController.getInstance().getMoney() + GameController.getInstance().getIncome());
        alert.initializeAlert("Get $ " + GameController.getInstance().getIncome(), Config.BLUE);
        alert.initializeAlert("+ " + (chips * mult) + " score", Config.BLUE);
        mySideBar.updateMoney();

        if (GameController.getInstance().getHandSizeReset() == 0) {
            GameController.getInstance().getPlayer().getHand().setHandSize(Config.DefaultHandSize);
        }else{
            GameController.getInstance().setHandSizeReset(Math.max(0, GameController.getInstance().getHandSizeReset() - 1));
        }

        GameController.getInstance().getPlayer().getHand().fillHand(GameController.getInstance().getPlayer().getDeck());
        mySideBar.updatePlayerScore();
        GameController.getInstance().setPlayHand(GameController.getInstance().getPlayHand() - 1);
        GameController.getInstance().getPlayer().getHand().getSelectedCards().clear();
        GameController.getInstance().getPlayer().getHand().setSelectedCards(GameController.getInstance().getPlayer().getHand().getSelectedCards());
        System.out.println("Play Function Score : " + GameController.getInstance().getPlayer().getScore() + " Stage : " + GameController.getInstance().getBlind().getReqScore());

        if (GameController.getInstance().getPlayer().getScore() >= GameController.getInstance().getBlind().getReqScore()) {
            GameController.getInstance().getBlind().setBlindNo(GameController.getInstance().getBlind().getBlindNo() + 1);
            GameController.getInstance().getBlind().initReqScore();
            GameController.getInstance().getPlayer().setScore(0);
            GameController.getInstance().setTheTowerSetter(false);
            mySideBar.updateRound();
            GameController.getInstance().setPlayHand(GameController.getInstance().getPlayer().getPlayRound());
            GameController.getInstance().setMoney(GameController.getInstance().getPlayer().getStartingMoney());
            GameController.getInstance().setDiscard(GameController.getInstance().getPlayer().getDiscardRound());
            mySideBar.updateSideBar();
            eventScreen.showWinningScreen(stackPane, root, mySideBar);
            GameController.getInstance().setMoney(GameController.getInstance().getPlayer().getStartingMoney());
            GameController.getInstance().setDiscard(GameController.getInstance().getPlayer().getDiscardRound());
            GameController.getInstance().setPlayHand(GameController.getInstance().getPlayer().getPlayRound());
            GameController.getInstance().setIncome(GameController.getInstance().getPlayer().getStartingIncome());
        }

        GameUtils.calculateScoreCard();
        mySideBar.updateSideBar();

        if (GameController.getInstance().getPlayHand() <= 0 && GameController.getInstance().getPlayer().getScore() < GameController.getInstance().getBlind().getReqScore()) {
            eventScreen.showLosingScreen(stackPane, root, mySideBar);
            mySideBar.updateSideBar();
        }
    } // :playCard

    // Discard Card
    public void discardCard(ArrayList<Card> cardSelected) {
        for (Card card : GameController.getInstance().getPlayer().getHand().getSelectedCards()) {
            GameController.getInstance().getPlayer().getHand().getCardList().remove(card);
        }
        GameController gameInstance = GameController.getInstance();
        gameInstance.setDiscard(gameInstance.getDiscard() - 1);
        cardSelected.clear();
        gameInstance.getPlayer().getHand().fillHand(gameInstance.getPlayer().getDeck());
        cardDiv.updateCardDiv(mySideBar);
    } // : discardCard

    // Launch the Game
    public void start(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        //init game
        GameController.getInstance().initGameVar();

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
            if (GameController.getInstance().getPlayer().getHand().getSelectedCards().isEmpty()) {
                alert.initializeAlert("Please select card!", Config.YELLLOW);
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
            if (GameController.getInstance().getPlayer().getHand().getSelectedCards().isEmpty()) {
                alert.initializeAlert("at least 1 card", Config.YELLLOW);
            } else if (GameController.getInstance().getDiscard() <= 0) {
                alert.initializeAlert("out of discard!", Config.YELLLOW);
                return;
            }
            discardCard(GameController.getInstance().getPlayer().getHand().getSelectedCards());
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
