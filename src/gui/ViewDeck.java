package gui;

import application.Suit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.card.Card;
import logic.game.GameController;

import java.util.List;

import org.w3c.dom.Text;

public class ViewDeck {
    GameController gameInstance = GameController.getInstance();


    public void displayCardDeckPopup() {
        StackPane cardDeckPopup = new StackPane();
        VBox viewDeckPanel = new VBox();
        viewDeckPanel.setAlignment(Pos.CENTER);
        viewDeckPanel.setId("view-deck-panel");
        viewDeckPanel.getStyleClass().add("view-deck-panel");
        viewDeckPanel.setPrefWidth(300);
        cardDeckPopup.setStyle("-fx-background-color: transparent;"); // Set StackPane background to transparent
        viewDeckPanel.setAlignment(Pos.TOP_CENTER);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            Stage stage = (Stage) cardDeckPopup.getScene().getWindow();
            stage.close();
        });

        Button cancelView = new Button("x");

        cancelView.setOnAction(e -> {
            viewDeckPanel.setVisible(false);
        });

        List<Card> deck = gameInstance.getPlayer().getDeck().getInitDeck();
        List<Card> handCard = gameInstance.getPlayer().getHand().getCardList();

        HBox cardDivClubs = new HBox();
        cardDivClubs.setAlignment(Pos.CENTER);
        cardDivClubs.setPadding(new Insets(0, 10, 10, 10)); // Increase bottom padding to move cardDiv down
        cardDivClubs.setSpacing(-30);
        cardDivClubs.setPrefWidth(100);
        cardDivClubs.setPrefHeight(100);
        for (Card card : deck) {
            if (card.getSuit() == Suit.CLUBS) {
                ImageView cardImageView = new ImageView(CardImage.getCardImage(card.toString()));
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
        cardDivDiamonds.setSpacing(-30);
        cardDivDiamonds.setPrefWidth(100);
        cardDivDiamonds.setPrefHeight(100);
        for (Card card : deck) {
            if (card.getSuit() == Suit.DIAMONDS) {
                ImageView cardImageView = new ImageView(CardImage.getCardImage(card.toString()));
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
        cardDivHearts.setSpacing(-30);
        cardDivHearts.setPrefWidth(100);
        cardDivHearts.setPrefHeight(100);
        for (Card card : deck) {
            if (card.getSuit() == Suit.HEARTS) {
                ImageView cardImageView = new ImageView(CardImage.getCardImage(card.toString()));
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

        HBox cardDivSpades = new HBox();
        cardDivSpades.setAlignment(Pos.CENTER);
        cardDivSpades.setPadding(new Insets(0, 10, 30, 10)); // Increase bottom padding to move cardDiv down
        cardDivSpades.setSpacing(-30);
        cardDivSpades.setPrefWidth(100);
        cardDivSpades.setPrefHeight(100);
        for (Card card : deck) {
            if (card.getSuit() == Suit.SPADES) {
                ImageView cardImageView = new ImageView(CardImage.getCardImage(card.toString()));
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

        viewDeckPanel.getChildren().addAll( closeButton,cardDivClubs, cardDivDiamonds, cardDivHearts, cardDivSpades);
        cardDeckPopup.getChildren().addAll(viewDeckPanel);


        Scene scene = new Scene(cardDeckPopup, 1000, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
