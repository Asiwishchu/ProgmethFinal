 package logic.main;


import application.HandType;
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
import logic.game.CardClassifier;
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
import logic.game.Config;
import logic.game.GameController;
import logic.tarot.Tarot;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {
    GameController gameInstance = GameController.getInstance();
    ArrayList<Card> cardSelection = gameInstance.getPlayer().getHand().getSelectedCards();

    private StackPane stackPane = new StackPane();
    private HBox root = new HBox(30);
    VBox alertSection = new VBox(10);

    SideBar mySideBar = new SideBar();
    EventScreen eventScreen = new EventScreen();

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
        //TODO update tarot div


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
        System.out.println("Play Function Score : " + gameInstance.getPlayer().getScore() + " Stage : " + gameInstance.getBlind().getReqScore());
        if (gameInstance.getPlayer().getScore() >= gameInstance.getBlind().getReqScore()) {
            gameInstance.getBlind().setBlindNo(gameInstance.getBlind().getBlindNo() + 1);
            gameInstance.getPlayer().setScore(0);
            mySideBar.updateRound();
            gameInstance.setPlayHand(gameInstance.getPlayer().getPlayRound());
            eventScreen.showWinningScreen(stackPane, root);
        }
        mySideBar.updateHand();

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
    } // : discardCard


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

        // tarot Description
        StackPane tarotDescriptionStackPane = new StackPane();
        Rectangle tarotDescriptionBox = new Rectangle(640, 130, Color.web("1E1E1E"));
        tarotDescriptionStackPane.setPadding(new Insets(0, 0,0,0));
        tarotDescriptionBox.setStroke(Color.web("3E4043"));
        tarotDescriptionBox.setStrokeWidth(3);
        tarotDescriptionBox.setArcHeight(10);
        tarotDescriptionBox.setArcWidth(10);


        // Play Zone =================
        VBox playZone = new VBox(0); // Adjust spacing as needed
        playZone.setAlignment(Pos.BOTTOM_CENTER);


//        //tarot div
//        HBox tarotDiv = new HBox();
//        tarotDiv.setAlignment(Pos.CENTER);
//        tarotDiv.setSpacing(20);
//
//
//        Tarot[] tarots = GameController.createNewTarot(5);
//
//        for (Tarot tarot : tarots) {
//            tarot.getDescription();
//            ImageView tarotImage = new ImageView(tarot.getImage());
//            tarotImage.setFitHeight(190);
//            tarotImage.setFitWidth(120);
//            tarotDiv.getChildren().add(tarotImage);
//
//            // tarot Description
//            Text tarotCardName = new Text();
//            tarotCardName.setText(tarot.getName());
//            tarotCardName.setId("tarot-card-name");
//
//            Text tarotCardAbility = new Text();
//            tarotCardAbility.setText(tarot.getDescription());
//
//            tarotCardAbility.setId("tarot-card-ability");
//            VBox tarotDescriptionVBox = new VBox(20);
//            tarotDescriptionVBox.setPadding(new Insets(20,0,0,50));
//            tarotDescriptionStackPane.getStylesheets().add(getClass().getResource("/tarotDescription.css").toExternalForm());
//
//
//            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), tarotImage);
//            scaleIn.setToX(1.07);
//            scaleIn.setToY(1.07);
//            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), tarotImage);
//            scaleOut.setToX(1);
//            scaleOut.setToY(1);
//
//            AtomicBoolean isScaled = new AtomicBoolean(false); // Flag to track if card is scaled
//
//            tarotImage.setOnMouseClicked(e -> {
//                if(!isScaled.get() && gameInstance.getMoney() < tarot.getCost()) return; //no money & not selected
//                else if (isScaled.get()) {
//                    scaleOut.play();
//                    isScaled.set(false);
//                    gameInstance.getSelectedTarots().remove(tarot);
//                    tarotDescriptionVBox.getChildren().clear();
//                    tarotDescriptionVBox.getChildren().addAll(tarotCardName, tarotCardAbility);
//                    tarotDescriptionStackPane.getChildren().clear();
//                    tarotDescriptionStackPane.getChildren().addAll(tarotDescriptionBox, tarotDescriptionVBox);
//                    gameInstance.setMoney(gameInstance.getMoney() + tarot.getCost());// return money when unselected
//                } else {
//                    scaleIn.play();
//                    isScaled.set(true);
//                    gameInstance.setMoney(gameInstance.getMoney() - tarot.getCost());
//                    tarotDescriptionVBox.getChildren().clear();
//                    tarotDescriptionVBox.getChildren().addAll(tarotCardName, tarotCardAbility);
//                    tarotDescriptionStackPane.getChildren().clear();
//                    tarotDescriptionStackPane.getChildren().addAll(tarotDescriptionBox, tarotDescriptionVBox);
//                    gameInstance.getSelectedTarots().add(tarot);// add tarot to selected tarots
//                }
//            });
//
//        }
//
//        tarotDiv.setPrefWidth(600);
//        tarotDiv.setPrefHeight(250);
//        tarotDiv.setPadding(new Insets(0, 30, 0, 0));
//
//        gameInstance.refillTarots();
//        gameInstance.setSelectedTarots(new ArrayList<>());
//
//
//        HBox cardDiv = new HBox();
//        cardDiv.setAlignment(Pos.CENTER);
//        cardDiv.setPadding(new Insets(0, 30, 10, 0));
//        cardDiv.setSpacing(-60);
//        cardDiv.setPrefWidth(100);
//        cardDiv.setPrefHeight(50);
//        // ============================


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
                updateCardDiv(cardDiv);
            } else {
                AlertMessage.initializeAlert("Please select card!", stackPane, root, alertSection);
            }
            clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
            clickMediaPlayer.play();
        });

        Button discardButton = new Button("Discard");
        discardButton.setId("discardButton"); // Set ID for discard button
        discardButton.setPadding(new Insets(5,10,5,10));
        discardButton.setOnAction(e -> {
            if (cardSelection.isEmpty()) {
                AlertMessage.initializeAlert("at least 1 card", stackPane, root, alertSection);
            } else if (gameInstance.getDiscard() <= 0) {
                AlertMessage.initializeAlert("out of discard!", stackPane, root, alertSection);
                return;
            }
            clickMediaPlayer.seek(clickMediaPlayer.getStartTime());
            clickMediaPlayer.play();
            discardCard(cardSelection);
            updateCardDiv(cardDiv);
            mySideBar.updateDiscard();
        });
        // =============================


        // Zone Node ADDED
        buttonZone.getChildren().addAll(playButton, discardButton);
        playZone.getChildren().addAll(tarotDescriptionStackPane,tarotDiv,cardDiv, buttonZone);
        root.getChildren().addAll(mySideBar.initializeSidebar(stackPane, root), playZone);

        // Render Hand Card list ===========
        updateCardDiv(cardDiv);
        //=================================

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
