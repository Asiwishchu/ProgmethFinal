package gui;

import application.Suit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.card.Card;
import logic.game.GameController;

import java.util.List;

import org.w3c.dom.Text;

public class ViewDeck {
    GameController gameInstance = GameController.getInstance();


    public void displayCardDeckPopup(StackPane stackPane, HBox root) {;
        VBox viewDeckPanel = new VBox();
        viewDeckPanel.setAlignment(Pos.CENTER);
        viewDeckPanel.setId("view-deck-panel");
        viewDeckPanel.getStyleClass().add("view-deck-panel");
        viewDeckPanel.setPrefWidth(1000);
        viewDeckPanel.setPrefHeight(600);
        viewDeckPanel.setAlignment(Pos.CENTER);
        Rectangle viewDeckFade = new  Rectangle(1000, 600, Color.BLACK);
        viewDeckFade.setOpacity(0.6);
        Rectangle viewDeckBox = new Rectangle(900, 500, Color.web("2E333A"));
        viewDeckBox.setOpacity(0.8);
        viewDeckBox.setArcWidth(15);
        viewDeckBox.setArcHeight(15);
        viewDeckBox.setStroke(Color.web("595D64"));
        viewDeckBox.setStrokeWidth(3);

        Button closeButton = new Button("Close");
        closeButton.setTextFill(Color.web("2E333A"));
        closeButton.setStyle("-fx-background-color: #595D64");

        closeButton.setOnAction(e -> {
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
        });
        VBox closeButtonVBox = new VBox(closeButton);
        closeButtonVBox.setPrefHeight(600);
        closeButtonVBox.setPrefWidth(1000);
        closeButtonVBox.setAlignment(Pos.BOTTOM_CENTER);
        closeButtonVBox.setPadding(new Insets(0,0,20,0));


        Button cancelView = new Button("x");

        cancelView.setOnAction(e -> {
            viewDeckPanel.setVisible(false);
        });

        List<Card> deck = gameInstance.getPlayer().getDeck().getInitDeck();
        List<Card> handCard = gameInstance.getPlayer().getHand().getCardList();

        HBox cardDivClubs = new HBox();
        cardDivClubs.setAlignment(Pos.CENTER);
        cardDivClubs.setPadding(new Insets(0, 10, 10, 10)); // Increase bottom padding to move cardDiv down
        cardDivClubs.setSpacing(-25);
        cardDivClubs.setPrefWidth(100);
        cardDivClubs.setPrefHeight(100);
        for (Card card : deck) {
            if (card.getSuit() == Suit.CLUBS) {
                ImageView cardImageView = new ImageView(card.getCardImage());
                cardImageView.setFitWidth(90);
                cardImageView.setFitHeight(90);

                for (Card handcard : handCard) {
                    if (card.getRank().equals(handcard.getRank()) && card.getSuit().equals(handcard.getSuit())) {
                        cardImageView.setOpacity(0.2);
                    }
                }
                cardDivClubs.getChildren().add(cardImageView);
            }
        }

        HBox cardDivDiamonds = new HBox();
        cardDivDiamonds.setAlignment(Pos.CENTER);
        cardDivClubs.setPadding(new Insets(30, 10, 10, 10)); // Increase bottom padding to move cardDiv down
        cardDivDiamonds.setSpacing(-25);
        cardDivDiamonds.setPrefWidth(90);
        cardDivDiamonds.setPrefHeight(90);
        for (Card card : deck) {
            if (card.getSuit() == Suit.DIAMONDS) {
                ImageView cardImageView = new ImageView(card.getCardImage());
                cardImageView.setFitWidth(90);
                cardImageView.setFitHeight(90);

                for (Card handcard : handCard) {
                    if (card.getRank().equals(handcard.getRank()) && card.getSuit().equals(handcard.getSuit())) {
                        cardImageView.setOpacity(0.2);
                    }
                }

                cardDivDiamonds.getChildren().add(cardImageView);
            }
        }

        HBox cardDivHearts = new HBox();
        cardDivHearts.setAlignment(Pos.CENTER);
        cardDivHearts.setPadding(new Insets(10, 10, 10, 10)); // Increase bottom padding to move cardDiv down
        cardDivHearts.setSpacing(-25);
        cardDivHearts.setPrefWidth(100);
        cardDivHearts.setPrefHeight(100);
        for (Card card : deck) {
            if (card.getSuit() == Suit.HEARTS) {
                ImageView cardImageView = new ImageView(card.getCardImage());
                cardImageView.setFitWidth(90);
                cardImageView.setFitHeight(90);

                for (Card handcard : handCard) {
                    if (card.getRank().equals(handcard.getRank()) && card.getSuit().equals(handcard.getSuit())) {
                        cardImageView.setOpacity(0.2);
                    }
                }

                cardDivHearts.getChildren().add(cardImageView);
            }
        }

        HBox cardDivSpades = new HBox();
        cardDivSpades.setAlignment(Pos.CENTER);
        cardDivSpades.setPadding(new Insets(0, 10, 30, 10)); // Increase bottom padding to move cardDiv down
        cardDivSpades.setSpacing(-25);
        cardDivSpades.setPrefWidth(90);
        cardDivSpades.setPrefHeight(90);
        for (Card card : deck) {
            if (card.getSuit() == Suit.SPADES) {
                ImageView cardImageView = new ImageView(card.getCardImage());
                cardImageView.setFitWidth(90);
                cardImageView.setFitHeight(90);

                for (Card handcard : handCard) {
                    if (card.getRank().equals(handcard.getRank()) && card.getSuit().equals(handcard.getSuit())) {
                        cardImageView.setOpacity(0.2);
                    }
                }

                cardDivSpades.getChildren().add(cardImageView);
            }
        }
        stackPane.setAlignment(Pos.CENTER);
        viewDeckPanel.getChildren().addAll(cardDivClubs, cardDivDiamonds, cardDivHearts, cardDivSpades);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(root,viewDeckFade,viewDeckBox, viewDeckPanel, closeButtonVBox);

    }
}
