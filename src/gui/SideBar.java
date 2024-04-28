package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.game.GameController;

public class SideBar {
    GameController gameInstance = GameController.getInstance();
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
        VBox yourScoreVBox = new VBox();
        yourScoreVBox.setPrefHeight(120);
        Text yourScoreText = new Text("Your score");
        yourScoreText.getStyleClass().add("your-score-text-style");
        StackPane yourScoreNumberStackPane = new StackPane();
        Rectangle yourScoreNumberBox = new Rectangle(200,60, Color.web("#2E333A"));
        yourScoreNumberBox.setArcWidth(10);
        yourScoreNumberBox.setArcHeight(10);

        Text yourScoreNumberText = new Text(Integer.toString(gameInstance.getPlayer().getScore()));
        yourScoreNumberText.getStyleClass().add("your-score-number-text-style");
        yourScoreNumberStackPane.getChildren().addAll(yourScoreNumberBox, yourScoreNumberText);
        yourScoreVBox.getChildren().addAll(yourScoreText,yourScoreNumberStackPane);
        yourScoreStackPane.getChildren().addAll(yourScoreBox, yourScoreVBox);


        sideBarDiv.getChildren().addAll(blindBoxStackPane,goalBoxStackPane, yourScoreStackPane);

        return sideBarDiv;
    }
}
