package gui;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import logic.card.Card;
import logic.game.Config;
import logic.game.GameController;
import utils.GameUtils;

import java.util.concurrent.atomic.AtomicBoolean;

public class CardDiv {
    HBox cardDiv = new HBox();
    MediaPlayer unselectMediaPlayer = new MediaPlayer(new Media(getClass().getResource("/Sound/unselectCard.mp3").toString()));
    MediaPlayer selectMediaPlayer = new MediaPlayer(new Media(getClass().getResource("/Sound/cardSelecting.mp3").toString()));

    // Card Rendering Function
    public void updateCardDiv(SideBar mySideBar) {
        cardDiv.getChildren().clear();

        for (Card card : GameController.getInstance().getPlayer().getHand().getCardList()) {
            ImageView cardImageView = new ImageView(card.getImage());

            // Hover effect
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(100), cardImageView);
            scaleIn.setToX(1.1);
            scaleIn.setToY(1.1);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(100), cardImageView);
            scaleOut.setToX(1);
            scaleOut.setToY(1);

            AtomicBoolean isScaled = new AtomicBoolean(false); // Flag to track if card is scaled


            cardImageView.setOnMouseClicked(e -> {
                if (!isScaled.get() && GameController.getInstance().getPlayer().getHand().getSelectedCards().size() >= 5){
                    GameController.getInstance().getAlert().initializeAlert("Cannot select card\nmore than 5", Config.YELLLOW);
                }
                else if (isScaled.get()) {
                    scaleOut.play();
                    isScaled.set(false);
                    unselectMediaPlayer.seek(unselectMediaPlayer.getStartTime());
                    unselectMediaPlayer.play();
                    GameController.getInstance().getPlayer().getHand().getSelectedCards().remove(card);
                }
                else {
                    scaleIn.play();
                    isScaled.set(true);
                    selectMediaPlayer.seek(selectMediaPlayer.getStartTime());
                    selectMediaPlayer.play();
                    GameController.getInstance().getPlayer().getHand().getSelectedCards().add(card);
                }
                GameUtils.calculateScoreCard();
                mySideBar.updateCardToPlay();
            });

            cardImageView.setOnMouseEntered(e -> {
                cardImageView.setTranslateY(-10);
            });
            cardImageView.setOnMouseExited(e -> {
                cardImageView.setTranslateY(0);
            });

            cardImageView.setFitWidth(180);
            cardImageView.setFitHeight(180);
            cardDiv.getChildren().add(cardImageView);
        }
        cardDiv.setAlignment(Pos.CENTER);
        cardDiv.setPadding(new Insets(0, 0, 30, 0));
        cardDiv.setSpacing(-80);
    } // :updateCardDiv


    // Card Rendering Function
    public HBox initializeCardDiv(SideBar mySideBar) {
        GameController gameInstance = GameController.getInstance();
        cardDiv.getChildren().clear();

        for (Card card : gameInstance.getPlayer().getHand().getCardList()) {
            ImageView cardImageView = new ImageView(card.getImage());

            // Hover effect
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(100), cardImageView);
            scaleIn.setToX(1.1);
            scaleIn.setToY(1.1);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(100), cardImageView);
            scaleOut.setToX(1);
            scaleOut.setToY(1);

            AtomicBoolean isScaled = new AtomicBoolean(false); // Flag to track if card is scaled


            cardImageView.setOnMouseClicked(e -> {
                if (!isScaled.get() && gameInstance.getPlayer().getHand().getSelectedCards().size() >= 5){
                    GameController.getInstance().getAlert().initializeAlert("Cannot select card\nmore than 5", Config.YELLLOW);
                }
                else if (isScaled.get()) {
                    scaleOut.play();
                    isScaled.set(false);
                    unselectMediaPlayer.seek(unselectMediaPlayer.getStartTime());
                    unselectMediaPlayer.play();
                    gameInstance.getPlayer().getHand().getSelectedCards().remove(card);
                } else {
                    scaleIn.play();
                    isScaled.set(true);
                    selectMediaPlayer.seek(selectMediaPlayer.getStartTime());
                    selectMediaPlayer.play();
                    gameInstance.getPlayer().getHand().getSelectedCards().add(card);
                }
                GameUtils.calculateScoreCard();
                mySideBar.updateCardToPlay();
            });

            cardImageView.setOnMouseEntered(e -> {
                cardImageView.setTranslateY(-10);
            });
            cardImageView.setOnMouseExited(e -> {
                cardImageView.setTranslateY(0);
            });

            cardImageView.setFitWidth(180);
            cardImageView.setFitHeight(180);
            cardDiv.getChildren().add(cardImageView);
        }
        cardDiv.setAlignment(Pos.CENTER);
        cardDiv.setPadding(new Insets(0, 0, 30, 0));
        cardDiv.setSpacing(-80);
        return  cardDiv;
    } // :initializeCardDiv
}
