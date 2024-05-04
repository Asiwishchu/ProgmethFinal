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


public class EventScreen {
    public void showWinningScreen(StackPane stackPane, HBox root, int blinRound, int goal){
        Rectangle winningFade = new Rectangle(1000,600);
        winningFade.setOpacity(0.8);
        Rectangle winningStripe = new Rectangle(1000, 200, Color.web("41454A"));
        winningStripe.setOpacity(0.8);
        VBox winningVBox = new VBox(30);
        Text youWinText = new Text("You win Blind " + blinRound);
        youWinText.setId("you-win-text");
        Text nextGoalText = new Text("Your next goal is " + goal);
        nextGoalText.setId("next-goal-text");
        stackPane.getChildren().clear();
        winningVBox.getChildren().addAll(youWinText,nextGoalText);
        winningVBox.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(root, winningFade, winningStripe, winningVBox);
        stackPane.setAlignment(Pos.CENTER);
        winningVBox.getStylesheets().add(getClass().getResource("/event.css").toExternalForm());
    }

    public void showLosingScreen(StackPane stackPane, HBox root){
        Rectangle losingFade = new Rectangle(1000,600,Color.BLACK);
        losingFade.setOpacity(0.9);
        VBox losingVBox = new VBox(30);
        Text gameOverText = new Text("Game Over");
        gameOverText.setId("game-over-title");
        Text totalScoreText = new Text("Total Score");
        totalScoreText.setId("game-over-sub-title");
        Text totalScoreAmount = new Text("2000");
        totalScoreAmount.setId("game-over-sub-title");
        Button retryButton = new Button("Retry?");
        retryButton.setPadding(new Insets(15,20,15,20));
        retryButton.setId("retry-button");
        losingVBox.getChildren().addAll(gameOverText, totalScoreText, totalScoreAmount, retryButton);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(root,losingFade,losingVBox);
        losingVBox.setAlignment(Pos.CENTER);
        losingVBox.getStylesheets().add(getClass().getResource("/event.css").toExternalForm());
    }

    public void showPowerUpScreen(StackPane stackPane, HBox root){
        Rectangle powerUpFade = new Rectangle(1000,600,Color.BLACK);
        powerUpFade.setOpacity(0.9);
        VBox powerUpVBox = new VBox(30);
        Text powerUpText = new Text("Choose your reward");
        powerUpText.setId("game-over-title");

        powerUpVBox.getChildren().addAll(powerUpText);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(root,powerUpFade,powerUpVBox);
        powerUpVBox.setAlignment(Pos.CENTER);
        powerUpVBox.getStylesheets().add(getClass().getResource("/event.css").toExternalForm());
    }
}
