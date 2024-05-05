package gui;

import application.AlertHandler;
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
    AlertHandler alertHandler;



    public TarotDiv(AlertHandler alertHandler) {
        this.alertHandler = alertHandler;
    }

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
            StackPane tarotAndMoney = new StackPane();
            tarotDiv.getChildren().add(tarotAndMoney);
            Rectangle moneyBox = new Rectangle(120,190,Color.web("1E1E1E"));
            moneyBox.setOpacity(0.7);
            moneyBox.setVisible(false);
            moneyBox.setArcWidth(10);
            moneyBox.setArcHeight(10);
            Text moneyText = new Text();
            moneyText.setText("$ " + tarot.getCost());
            moneyText.setStyle("-fx-fill: yellow; -fx-font-family: \"Jersey 10\", sans-serif; -fx-font-size: 48px;");
            moneyText.setVisible(false);
            tarotAndMoney.getChildren().addAll(tarotImage,moneyBox, moneyText);
            tarotAndMoney.setAlignment(Pos.CENTER);

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


            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), tarotAndMoney);
            scaleIn.setToX(1.07);
            scaleIn.setToY(1.07);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), tarotAndMoney);
            scaleOut.setToX(1);
            scaleOut.setToY(1);

            AtomicBoolean isScaled = new AtomicBoolean(false); // Flag to track if card is scaled

            tarotAndMoney.setOnMouseClicked(e -> {
                if(!isScaled.get() && gameInstance.getMoney() < tarot.getCost()){
                    alertHandler.initializeAlert("Insufficient fund");//no money & not selected
                }
                else if (isScaled.get()) {
                    scaleOut.play();
                    isScaled.set(false);
                    gameInstance.getSelectedTarots().remove(tarot);
                    gameInstance.setMoney(gameInstance.getMoney() + tarot.getCost());
                } else {
                    scaleIn.play();
                    isScaled.set(true);
                    gameInstance.setMoney(gameInstance.getMoney() - tarot.getCost());
                    gameInstance.getSelectedTarots().add(tarot);
                }
            });

            tarotAndMoney.setOnMouseEntered(e -> {
                tarotDescriptionVBox.getChildren().clear();
                tarotDescriptionVBox.getChildren().addAll(tarotCardName, tarotCardAbility);
                tarotDescriptionStackPane.getChildren().clear();
                tarotDescriptionStackPane.getChildren().addAll(tarotDescriptionBox, tarotDescriptionVBox);
                tarotDescriptionStackPane.setVisible(true);
                tarotImage.setTranslateY(-5);
                moneyText.setVisible(true);
                moneyBox.setVisible(true);
                moneyBox.setTranslateY(-5);
            });
            tarotAndMoney.setOnMouseExited(e -> {
                if(isScaled.get()){
                    moneyText.setVisible(true);
                    moneyBox.setVisible(true);
                }else{
                    moneyText.setVisible(false);
                    moneyBox.setVisible(false);
                }
                tarotImage.setTranslateY(0);
                moneyBox.setTranslateY(0);
                tarotDescriptionStackPane.setVisible(false);
            });

        }

        tarotDiv.setPrefWidth(600);
        tarotDiv.setPrefHeight(250);
        tarotDiv.setPadding(new Insets(0, 30, 0, 0));

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
            StackPane tarotAndMoney = new StackPane();
            tarotDiv.getChildren().add(tarotAndMoney);
            Rectangle moneyBox = new Rectangle(120,190,Color.web("1E1E1E"));
            moneyBox.setOpacity(0.7);
            moneyBox.setVisible(false);
            moneyBox.setArcWidth(10);
            moneyBox.setArcHeight(10);
            Text moneyText = new Text();
            moneyText.setText("$ " + tarot.getCost());
            moneyText.setStyle("-fx-fill: yellow; -fx-font-family: \"Jersey 10\", sans-serif; -fx-font-size: 48px;");
            moneyText.setVisible(false);
            tarotAndMoney.getChildren().addAll(tarotImage,moneyBox, moneyText);
            tarotAndMoney.setAlignment(Pos.CENTER);

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


            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), tarotAndMoney);
            scaleIn.setToX(1.07);
            scaleIn.setToY(1.07);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), tarotAndMoney);
            scaleOut.setToX(1);
            scaleOut.setToY(1);

            AtomicBoolean isScaled = new AtomicBoolean(false); // Flag to track if card is scaled

            tarotAndMoney.setOnMouseClicked(e -> {
                if(!isScaled.get() && gameInstance.getMoney() < tarot.getCost()){
                    alertHandler.initializeAlert("Insufficient fund");//no money & not selected
                }
                else if (isScaled.get()) {
                    scaleOut.play();
                    isScaled.set(false);
                    gameInstance.getSelectedTarots().remove(tarot);
                    gameInstance.setMoney(gameInstance.getMoney() + tarot.getCost());
                } else {
                    scaleIn.play();
                    isScaled.set(true);
                    gameInstance.setMoney(gameInstance.getMoney() - tarot.getCost());
                    gameInstance.getSelectedTarots().add(tarot);
                }
            });

            tarotAndMoney.setOnMouseEntered(e -> {
                    tarotDescriptionVBox.getChildren().clear();
                    tarotDescriptionVBox.getChildren().addAll(tarotCardName, tarotCardAbility);
                    tarotDescriptionStackPane.getChildren().clear();
                    tarotDescriptionStackPane.getChildren().addAll(tarotDescriptionBox, tarotDescriptionVBox);
                    tarotDescriptionStackPane.setVisible(true);
                tarotImage.setTranslateY(-5);
                moneyText.setVisible(true);
                moneyBox.setVisible(true);
                moneyBox.setTranslateY(-5);
            });
            tarotAndMoney.setOnMouseExited(e -> {
                if(isScaled.get()){
                    moneyText.setVisible(true);
                    moneyBox.setVisible(true);
                }else{
                    moneyText.setVisible(false);
                    moneyBox.setVisible(false);
                }
                tarotImage.setTranslateY(0);
                moneyBox.setTranslateY(0);
                tarotDescriptionStackPane.setVisible(false);
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
