package gui;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.game.GameController;
import logic.tarot.Tarot;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TarotDiv {
    GameController gameInstance = GameController.getInstance();
    HBox tarotDiv = new HBox();
    StackPane tarotDescriptionStackPane = new StackPane();
    Rectangle tarotDescriptionBox = new Rectangle(640, 130, Color.web("1E1E1E"));
    ArrayList<Tarot> tarots = GameController.createNewTarot(5);
    VBox tarotZone = new VBox();

    public void updateTarotDiv(){
        tarots = GameController.getInstance().getTarotArrayList();

        tarotDiv.setAlignment(Pos.CENTER);
        tarotDiv.setSpacing(20);
        tarotDescriptionBox.setStroke(Color.web("3E4043"));
        tarotDescriptionBox.setStrokeWidth(3);
        tarotDescriptionBox.setArcHeight(10);
        tarotDescriptionBox.setArcWidth(10);

        tarotDiv.getChildren().clear();


        for (Tarot tarot : tarots) {
            tarot.getDescription();
            ImageView tarotImage = new ImageView(tarot.getImage());
            tarotImage.setFitHeight(190);
            tarotImage.setFitWidth(120);
            tarotDiv.getChildren().add(tarotImage);

            // tarot Description
            Text tarotCardName = new Text();
            tarotCardName.setText(tarot.getName());
            tarotCardName.setId("tarot-card-name");

            Text tarotCardAbility = new Text();
            tarotCardAbility.setText(tarot.getDescription());

            tarotCardAbility.setId("tarot-card-ability");
            VBox tarotDescriptionVBox = new VBox(20);
            tarotDescriptionVBox.setPadding(new Insets(20,0,0,50));
            tarotDescriptionStackPane.getStylesheets().add(getClass().getResource("/tarotDescription.css").toExternalForm());


            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), tarotImage);
            scaleIn.setToX(1.07);
            scaleIn.setToY(1.07);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), tarotImage);
            scaleOut.setToX(1);
            scaleOut.setToY(1);

            AtomicBoolean isScaled = new AtomicBoolean(false); // Flag to track if card is scaled

            tarotImage.setOnMouseClicked(e -> {
                if(!isScaled.get() && gameInstance.getMoney() < tarot.getCost()) return; //no money & not selected
                else if (isScaled.get()) {
                    scaleOut.play();
                    isScaled.set(false);
                    gameInstance.getSelectedTarots().remove(tarot);
                    tarotDescriptionVBox.getChildren().clear();
                    tarotDescriptionVBox.getChildren().addAll(tarotCardName, tarotCardAbility);
                    tarotDescriptionStackPane.getChildren().clear();
                    tarotDescriptionStackPane.getChildren().addAll(tarotDescriptionBox, tarotDescriptionVBox);
                    gameInstance.setMoney(gameInstance.getMoney() + tarot.getCost());// return money when unselected
                } else {
                    scaleIn.play();
                    isScaled.set(true);
                    gameInstance.setMoney(gameInstance.getMoney() - tarot.getCost());
                    tarotDescriptionVBox.getChildren().clear();
                    tarotDescriptionVBox.getChildren().addAll(tarotCardName, tarotCardAbility);
                    tarotDescriptionStackPane.getChildren().clear();
                    tarotDescriptionStackPane.getChildren().addAll(tarotDescriptionBox, tarotDescriptionVBox);
                    gameInstance.getSelectedTarots().add(tarot);// add tarot to selected tarots
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
    }


    public VBox initializeTarotDiv(){
        tarotDiv.setAlignment(Pos.CENTER);
        tarotDiv.setSpacing(20);
        tarotDescriptionBox.setStroke(Color.web("3E4043"));
        tarotDescriptionBox.setStrokeWidth(3);
        tarotDescriptionBox.setArcHeight(10);
        tarotDescriptionBox.setArcWidth(10);


        ArrayList<Tarot> tarots = GameController.createNewTarot(5);

        for (Tarot tarot : tarots) {
            tarot.getDescription();
            ImageView tarotImage = new ImageView(tarot.getImage());
            tarotImage.setFitHeight(190);
            tarotImage.setFitWidth(120);
            tarotDiv.getChildren().add(tarotImage);

            // tarot Description
            Text tarotCardName = new Text();
            tarotCardName.setText(tarot.getName());
            tarotCardName.setId("tarot-card-name");

            Text tarotCardAbility = new Text();
            tarotCardAbility.setText(tarot.getDescription());

            tarotCardAbility.setId("tarot-card-ability");
            VBox tarotDescriptionVBox = new VBox(20);
            tarotDescriptionVBox.setPadding(new Insets(20,0,0,50));
            tarotDescriptionStackPane.getStylesheets().add(getClass().getResource("/tarotDescription.css").toExternalForm());


            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), tarotImage);
            scaleIn.setToX(1.07);
            scaleIn.setToY(1.07);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), tarotImage);
            scaleOut.setToX(1);
            scaleOut.setToY(1);

            AtomicBoolean isScaled = new AtomicBoolean(false); // Flag to track if card is scaled

            tarotImage.setOnMouseClicked(e -> {
                if(!isScaled.get() && gameInstance.getMoney() < tarot.getCost()) return; //no money & not selected
                else if (isScaled.get()) {
                    scaleOut.play();
                    isScaled.set(false);
                    gameInstance.getSelectedTarots().remove(tarot);
                    tarotDescriptionVBox.getChildren().clear();
                    tarotDescriptionVBox.getChildren().addAll(tarotCardName, tarotCardAbility);
                    tarotDescriptionStackPane.getChildren().clear();
                    tarotDescriptionStackPane.getChildren().addAll(tarotDescriptionBox, tarotDescriptionVBox);
                    gameInstance.setMoney(gameInstance.getMoney() + tarot.getCost());// return money when unselected
                } else {
                    scaleIn.play();
                    isScaled.set(true);
                    gameInstance.setMoney(gameInstance.getMoney() - tarot.getCost());
                    tarotDescriptionVBox.getChildren().clear();
                    tarotDescriptionVBox.getChildren().addAll(tarotCardName, tarotCardAbility);
                    tarotDescriptionStackPane.getChildren().clear();
                    tarotDescriptionStackPane.getChildren().addAll(tarotDescriptionBox, tarotDescriptionVBox);
                    gameInstance.getSelectedTarots().add(tarot);// add tarot to selected tarots
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
        tarotZone.getChildren().addAll(tarotDescriptionStackPane, tarotDiv);
        // ============================
        return tarotZone;
    }

}
