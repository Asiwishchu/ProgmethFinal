package gui;

import application.Suit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.card.Card;
import logic.game.GameController;
import org.w3c.dom.css.Rect;
import logic.main.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SideBar {
    GameController gameInstance = GameController.getInstance();
    Text yourScoreNumberText = new Text(Integer.toString(0));
    Text cardToPlayFirstNumText = new Text("0");
    Text cardToPlaySecondNumText = new Text("0");
    Text cardToPlayText = new Text("High Card");

    public void updatePlayerScore(int playerScore){
        yourScoreNumberText.setText(Integer.toString(playerScore));
    }

    public void updateCardToPlay(int chip, int multiplier, String playType){
        cardToPlayFirstNumText.setText(Integer.toString(chip));
        cardToPlaySecondNumText.setText(Integer.toString(multiplier));
        cardToPlayText.setText(playType);
    }

    public VBox initializeSidebar(){
        double topMargin = 10;
        double rightMargin = 20;
        double bottomMargin = 30;
        double leftMargin = 40;


        // SideBar Container
        VBox sideBarDiv = new VBox();
        sideBarDiv.setId("sideBarDiv");
        sideBarDiv.getStylesheets().add(getClass().getResource("/side_bar.css").toExternalForm());
        sideBarDiv.setPrefWidth(240);
        sideBarDiv.setAlignment(Pos.TOP_CENTER);
        Insets margins = new Insets(topMargin, rightMargin, bottomMargin, leftMargin);
        VBox.setMargin(sideBarDiv, margins);

        //BlindBox Container
        StackPane blindBoxStackPane = new StackPane();
        blindBoxStackPane.setPadding(new Insets(6,0,0,0));
        Rectangle blindBox = new Rectangle(225, 60, Color.web("03071C"));
        blindBox.setArcWidth(15);
        blindBox.setArcHeight(15);
        Text blindText = new Text("Blind "+gameInstance.getStage().getStageLv());

        blindText.getStyleClass().add("blind-text-style");
        blindBoxStackPane.getChildren().addAll(blindBox,blindText);

        //GoalBox Container
        StackPane goalBoxStackPane = new StackPane();
        goalBoxStackPane.setPadding(new Insets(10,0,0,0));
        HBox goalHBox = new HBox(10);
        goalHBox.setAlignment(Pos.CENTER);
        goalHBox.setPrefWidth(225);
        StackPane goalScoreStackPane = new StackPane();
        Rectangle goalBox = new Rectangle(225, 60, Color.web("03071C"));
        goalBox.setArcWidth(15);
        goalBox.setArcHeight(15);
        Text goalText = new Text("Goal");
        goalText.getStyleClass().add("goal-text-style");
        Rectangle goalScoreBox = new Rectangle(110,40, Color.WHITE);
        goalScoreBox.setArcWidth(10);
        goalScoreBox.setArcHeight(10);
        goalScoreBox.getStyleClass().add("goal-score-box-style");

        Text goalScoreNumberText = new Text(Integer.toString( gameInstance.getStage().getReqScore()));
        goalScoreNumberText.getStyleClass().add("goal-score-number-text-style");
        goalScoreStackPane.getChildren().addAll(goalScoreBox,goalScoreNumberText);
        goalHBox.getChildren().addAll(goalText,goalScoreStackPane);
        goalBoxStackPane.getChildren().addAll(goalBox, goalHBox);

        // Your Score Box - StackPane (Rec, VBOX(Text, StackPane(rectangle, Text)))
        StackPane yourScoreStackPane = new StackPane();
        yourScoreStackPane.setPadding(new Insets(10,0,0,0));
        Rectangle yourScoreBox = new Rectangle(225, 120, Color.web("#595D64"));
        yourScoreBox.setArcWidth(10);
        yourScoreBox.setArcHeight(10);
        VBox yourScoreVBox = new VBox(15);
        yourScoreVBox.setAlignment(Pos.CENTER);
        yourScoreVBox.setPrefHeight(120);
        Text yourScoreText = new Text("Your score");
        yourScoreText.getStyleClass().add("your-score-text-style");
        StackPane yourScoreNumberStackPane = new StackPane();
        Rectangle yourScoreNumberBox = new Rectangle(200,60, Color.web("#2E333A"));
        yourScoreNumberBox.setArcWidth(10);
        yourScoreNumberBox.setArcHeight(10);


        yourScoreNumberText.getStyleClass().add("your-score-number-text-style");
        yourScoreNumberStackPane.getChildren().addAll(yourScoreNumberBox, yourScoreNumberText);
        yourScoreVBox.getChildren().addAll(yourScoreText,yourScoreNumberStackPane);
        yourScoreStackPane.getChildren().addAll(yourScoreBox, yourScoreVBox);

        // Card play Container  - StackPane (Rec, VBox(Text, HBox(StackPane(Rec, Text),Text,StackPane(Rec, Text))))
        StackPane cardToPlayStackPane = new StackPane();
        cardToPlayStackPane.setPadding(new Insets(10,0,0,0));
        Rectangle cardToPlayBox = new Rectangle(225,120,Color.web("#595D64"));
        cardToPlayBox.setArcWidth(10);
        cardToPlayBox.setArcHeight(10);
        VBox cardToPlayVBox = new VBox(15);
        cardToPlayVBox.setAlignment(Pos.CENTER);
        cardToPlayVBox.setPrefWidth(225);
        cardToPlayVBox.setPrefHeight(120);

        cardToPlayText.getStyleClass().add("card-to-play-text-style");
        HBox cardToPlayHBox = new HBox(10);
        cardToPlayHBox.setPrefWidth(225);
        cardToPlayHBox.setAlignment(Pos.CENTER);
        StackPane cardToPlayFirstNumStackPane = new StackPane();
        Rectangle cardToPlayFirstNumBox = new Rectangle(80, 50, Color.web("#D9D9D9"));
        cardToPlayFirstNumBox.setArcWidth(10);
        cardToPlayFirstNumBox.setArcHeight(10);

        cardToPlayFirstNumText.getStyleClass().add("card-to-play-first-num-text-style");
        Text multiplyText = new Text("x");
        multiplyText.getStyleClass().add("multiply-text-style");
        StackPane cardToPlaySecondNumStackPane = new StackPane();
        Rectangle cardToPlaySecondNumBox = new Rectangle(80, 50, Color.web("#2E333A"));
        cardToPlaySecondNumBox.setArcWidth(10);
        cardToPlaySecondNumBox.setArcHeight(10);
        cardToPlaySecondNumText.getStyleClass().add("card-to-play-second-num-text-style");
        cardToPlaySecondNumStackPane.getChildren().addAll(cardToPlaySecondNumBox,cardToPlaySecondNumText);
        cardToPlayFirstNumStackPane.getChildren().addAll(cardToPlayFirstNumBox,cardToPlayFirstNumText);
        cardToPlayHBox.getChildren().addAll(cardToPlayFirstNumStackPane, multiplyText, cardToPlaySecondNumStackPane);
        cardToPlayVBox.getChildren().addAll(cardToPlayText, cardToPlayHBox);
        cardToPlayStackPane.getChildren().addAll(cardToPlayBox, cardToPlayVBox);

        // playStatus Container HBox(StackPane(Rec, VBox(Text, StackPane(Rec,Text))),StackPane(Rec, VBox(Text, StackPane(Rec,Text))))
        HBox playStatusHBox = new HBox(20);
        playStatusHBox.setAlignment(Pos.CENTER);
        playStatusHBox.setPrefWidth(225);
        playStatusHBox.setPrefHeight(120);
        VBox.setMargin(playStatusHBox, new Insets(10,0,0,0));
        StackPane handStatusStackPane = new StackPane();
        Rectangle handStatusBox = new Rectangle(90,90, Color.web("#26519D"));
        handStatusBox.setArcWidth(10);
        handStatusBox.setArcHeight(10);
        VBox handStatusVBox = new VBox(10);
        handStatusVBox.setAlignment(Pos.CENTER);
        handStatusVBox.setPrefWidth(90);
        handStatusVBox.setPrefHeight(90);
        Text handText = new Text("Hand");
        handText.getStyleClass().add("hand-text-style");
        StackPane handStatusNumStackPane = new StackPane();
        Rectangle handStatusNumBox = new Rectangle(80, 40, Color.web("#2E333A"));
        handStatusNumBox.setArcWidth(10);
        handStatusNumBox.setArcHeight(10);
        Text handStatusNumText = new Text(gameInstance.getPlayer().getPlayRound() + "");
        handStatusNumText.getStyleClass().add("hand-status-num-style");

        handStatusNumStackPane.getChildren().addAll(handStatusNumBox, handStatusNumText);
        handStatusVBox.getChildren().addAll(handText, handStatusNumStackPane);
        handStatusStackPane.getChildren().addAll(handStatusBox, handStatusVBox);


        StackPane dropStatusStackPane = new StackPane();
        Rectangle dropStatusBox = new Rectangle(90,90, Color.web("#BD2D2D"));
        dropStatusBox.setArcWidth(10);
        dropStatusBox.setArcHeight(10);
        VBox dropStatusVBox = new VBox(10);
        dropStatusVBox.setAlignment(Pos.CENTER);
        dropStatusVBox.setPrefWidth(90);
        dropStatusVBox.setPrefHeight(90);
        Text dropText = new Text("Drop");
        dropText.getStyleClass().add("drop-text-style");
        StackPane dropStatusNumStackPane = new StackPane();
        Rectangle dropStatusNumBox = new Rectangle(80, 40, Color.web("#2E333A"));
        dropStatusNumBox.setArcWidth(10);
        dropStatusNumBox.setArcHeight(10);
        Text dropStatusNumText = new Text(gameInstance.getPlayer().getDiscardRound()+"");
        dropStatusNumText.getStyleClass().add("drop-status-num-style");

        dropStatusNumStackPane.getChildren().addAll(dropStatusNumBox, dropStatusNumText);
        dropStatusVBox.getChildren().addAll(dropText, dropStatusNumStackPane);
        dropStatusStackPane.getChildren().addAll(dropStatusBox, dropStatusVBox);

        playStatusHBox.getChildren().addAll(handStatusStackPane,dropStatusStackPane);


        VBox viewDeckPanel = new VBox();
        viewDeckPanel.setAlignment(Pos.CENTER);
        viewDeckPanel.setId("view-deck-panel");
        viewDeckPanel.getStyleClass().add("view-deck-panel");
        viewDeckPanel.setPrefWidth(300);
        Rectangle viewBox = new Rectangle(700, 550, Color.rgb(46, 51, 58, 0.5));// Transparent background
        viewBox.setArcWidth(10);
        viewBox.setArcHeight(10);
        viewDeckPanel.setAlignment(Pos.TOP_CENTER);

        Button cancelView = new Button("x");

        cancelView.setOnAction(e -> {
            viewDeckPanel.setVisible(false);
        });

        //Sort Deck
        List<Card> deck = gameInstance.getPlayer().getDeck().getInitDeck();
        List<Card> handCard = gameInstance.getPlayer().getHand().getCardList();
//        deck.sort((Card p, Card q) -> {
//            if (p.getRank() != q.getRank()) {
//                return (p.getRank().ordinal() < q.getRank().ordinal() ? -1 : 1);
//            }
//            if (p.getSuit() != q.getSuit()) {
//                return (p.getSuit().ordinal() < q.getSuit().ordinal() ? -1 : 1);
//            }
//            return 0;
//        });

        HBox cardDivClubs = new HBox();
        cardDivClubs.setAlignment(Pos.CENTER);
        cardDivClubs.setPadding(new Insets(0, 10, 10, 10)); // Increase bottom padding to move cardDiv down
        cardDivClubs.setSpacing(-40);
        cardDivClubs.setPrefWidth(100);
        cardDivClubs.setPrefHeight(100);
        for (Card card : deck) {

            if (card.getSuit() == Suit.CLUBS) {
                ImageView cardImageView = new ImageView(card.getCardImage());
                cardImageView.setFitWidth(100);
                cardImageView.setFitHeight(100);

                for (Card handcard : handCard) {
                    if (card.getRank().equals(handcard.getRank()) && card.getSuit().equals(handcard.getSuit())) {
                        cardImageView.setVisible(false);
                    }
                }

                    cardDivClubs.getChildren().add(cardImageView);
            }
        }

        HBox cardDivDiamonds = new HBox();
        cardDivDiamonds.setAlignment(Pos.CENTER);
        cardDivClubs.setPadding(new Insets(30, 10, 10, 10)); // Increase bottom padding to move cardDiv down
        cardDivDiamonds.setSpacing(-40);
        cardDivDiamonds.setPrefWidth(100);
        cardDivDiamonds.setPrefHeight(100);
        for (Card card : deck) {
            if (card.getSuit() == Suit.DIAMONDS) {
                ImageView cardImageView = new ImageView(card.getCardImage());
                cardImageView.setFitWidth(100);
                cardImageView.setFitHeight(100);

                for (Card handcard : handCard) {
                    if (card.getRank().equals(handcard.getRank()) && card.getSuit().equals(handcard.getSuit())) {
                        cardImageView.setVisible(false);
                    }
                }

                cardDivDiamonds.getChildren().add(cardImageView);
            }
        }

        HBox cardDivHearts = new HBox();
        cardDivHearts.setAlignment(Pos.CENTER);
        cardDivHearts.setPadding(new Insets(10, 10, 10, 10)); // Increase bottom padding to move cardDiv down
        cardDivHearts.setSpacing(-40);
        cardDivHearts.setPrefWidth(100);
        cardDivHearts.setPrefHeight(100);
        for (Card card : deck) {
            if (card.getSuit() == Suit.HEARTS) {
                ImageView cardImageView = new ImageView(card.getCardImage());
                cardImageView.setFitWidth(100);
                cardImageView.setFitHeight(100);

                for (Card handcard : handCard) {
                    if (card.getRank().equals(handcard.getRank()) && card.getSuit().equals(handcard.getSuit())) {
                        cardImageView.setVisible(false);
                    }
                }

                cardDivHearts.getChildren().add(cardImageView);
            }
        }
//
       HBox cardDivSpades = new HBox();
        cardDivSpades.setAlignment(Pos.CENTER);
        cardDivSpades.setPadding(new Insets(0, 10, 30, 10)); // Increase bottom padding to move cardDiv down
        cardDivSpades.setSpacing(-40);
        cardDivSpades.setPrefWidth(100);
        cardDivSpades.setPrefHeight(100);
        for (Card card : deck) {
            if (card.getSuit() == Suit.SPADES) {
                ImageView cardImageView = new ImageView(card.getCardImage());
                cardImageView.setFitWidth(100);
                cardImageView.setFitHeight(100);

                for (Card handcard : handCard) {
                    if (card.getRank().equals(handcard.getRank()) && card.getSuit().equals(handcard.getSuit())) {
                        cardImageView.setVisible(false);
                    }
                }

                cardDivSpades.getChildren().add(cardImageView);
            }
        }


        //View Deck
        Button viewDeckButton = new Button("View Deck");
        viewDeckButton.getStyleClass().add("button-style");
        viewDeckButton.setPrefWidth(225);
        viewDeckButton.setPrefHeight(40);
        viewDeckButton.setOnAction(e ->{
                viewDeckPanel.getChildren().addAll(cancelView,cardDivClubs,cardDivDiamonds,cardDivHearts,cardDivSpades);
                viewDeckPanel.setVisible(true);
        });



        sideBarDiv.getChildren().addAll(viewDeckPanel,blindBoxStackPane,goalBoxStackPane, yourScoreStackPane, cardToPlayStackPane, playStatusHBox, viewDeckButton);

        return sideBarDiv;
    }
}
