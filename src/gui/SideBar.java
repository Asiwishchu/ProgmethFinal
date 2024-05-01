package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.card.Card;
import logic.game.GameController;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Stack;

public class SideBar {
    GameController gameInstance = GameController.getInstance();
    Text yourScoreNumberText = new Text(Integer.toString(0));

    public void updatePlayerScore(int playerScore){
        yourScoreNumberText.setText(Integer.toString(playerScore));
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
        Text cardToPlayText = new Text("High Card");
        cardToPlayText.getStyleClass().add("card-to-play-text-style");
        HBox cardToPlayHBox = new HBox(10);
        cardToPlayHBox.setPrefWidth(225);
        cardToPlayHBox.setAlignment(Pos.CENTER);
        StackPane cardToPlayFirstNumStackPane = new StackPane();
        Rectangle cardToPlayFirstNumBox = new Rectangle(80, 50, Color.web("#D9D9D9"));
        cardToPlayFirstNumBox.setArcWidth(10);
        cardToPlayFirstNumBox.setArcHeight(10);
        Text cardToPlayFirstNumText = new Text("10");
        cardToPlayFirstNumText.getStyleClass().add("card-to-play-first-num-text-style");
        Text multiplyText = new Text("x");
        multiplyText.getStyleClass().add("multiply-text-style");
        StackPane cardToPlaySecondNumStackPane = new StackPane();
        Rectangle cardToPlaySecondNumBox = new Rectangle(80, 50, Color.web("#2E333A"));
        cardToPlaySecondNumBox.setArcWidth(10);
        cardToPlaySecondNumBox.setArcHeight(10);
        Text cardToPlaySecondNumText = new Text("1");
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


        //View Deck
        Button viewDeckButton = new Button("View Deck");
        viewDeckButton.getStyleClass().add("button-style");
        viewDeckButton.setPrefWidth(225);
        viewDeckButton.setPrefHeight(40);




        sideBarDiv.getChildren().addAll(blindBoxStackPane,goalBoxStackPane, yourScoreStackPane, cardToPlayStackPane, playStatusHBox, viewDeckButton);

        return sideBarDiv;
    }
}
