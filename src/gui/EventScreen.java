package gui;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.game.GameController;


public class EventScreen {
    public void showWinningScreen(StackPane stackPane, HBox root){
        Rectangle winningFade = new Rectangle(1000,600);
        winningFade.setOpacity(0.8);
        Rectangle winningStripe = new Rectangle(1000, 200, Color.web("41454A"));
        winningStripe.setOpacity(0.8);
        VBox winningVBox = new VBox(30);
        Text youWinText = new Text("You win Blind " + (GameController.getInstance().getBlind().getBlindNo()-1));
        youWinText.setId("you-win-text");
        Text nextGoalText = new Text("Your next goal is " + GameController.getInstance().getBlind().getReqScore());
        nextGoalText.setId("next-goal-text");
        stackPane.getChildren().clear();
        winningVBox.getChildren().addAll(youWinText,nextGoalText);
        winningVBox.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(root, winningFade, winningStripe, winningVBox);
        stackPane.setAlignment(Pos.CENTER);
        winningVBox.getStylesheets().add(getClass().getResource("/event.css").toExternalForm());

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), winningVBox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), winningVBox);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> {
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
        });

        fadeIn.play();
        fadeIn.setOnFinished(e -> pause.play());
        pause.setOnFinished(e -> fadeOut.play());
    }

    public void showLosingScreen(StackPane stackPane, HBox root){
        Rectangle losingFade = new Rectangle(1000,600,Color.BLACK);
        losingFade.setOpacity(0.9);
        VBox losingVBox = new VBox(30);
        Text gameOverText = new Text("Game Over");
        gameOverText.setId("game-over-title");
        Text totalScoreText = new Text("Total Score");
        totalScoreText.setId("game-over-sub-title");
        Text totalScoreAmount = new Text(GameController.getInstance().getPlayer().getScore() + "");
        totalScoreAmount.setId("game-over-sub-title");
        Button retryButton = new Button("Retry?");
        retryButton.setOnAction(event -> {
            GameController.getInstance().resetGame();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
        });
        retryButton.setPadding(new Insets(15,20,15,20));
        retryButton.setId("retry-button");
        losingVBox.getChildren().addAll(gameOverText, totalScoreText, totalScoreAmount, retryButton);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(root,losingFade,losingVBox);
        losingVBox.setAlignment(Pos.CENTER);
        losingVBox.getStylesheets().add(getClass().getResource("/event.css").toExternalForm());
    }

    public void showRewardScreen(StackPane stackPane, HBox root){
        Rectangle rewardFade = new Rectangle(1000,600,Color.BLACK);
        rewardFade.setOpacity(0.9);
        VBox rewardVBox = new VBox(30);
        rewardVBox.setAlignment(Pos.CENTER);
        Text rewardText = new Text("Choose your reward");
        rewardText.setId("game-over-title");
        HBox rewardHBox = new HBox(20);
        rewardHBox.setAlignment(Pos.CENTER);

        StackPane handRewardStackPane = new StackPane();
        Rectangle handRewardBox = new Rectangle(200,280, Color.web("26519D"));
        handRewardBox.setArcHeight(10);
        handRewardBox.setArcWidth(10);
        handRewardBox.setOpacity(0.8);
        Text handRewardText = new Text("+1 Hand");
//        Image handRewardImage = new Image();
//        ImageView hanRewardImageView = new ImageView(handRewardImage)
        handRewardStackPane.getChildren().addAll(handRewardBox, handRewardText);

        StackPane discardRewardStackPane = new StackPane();
        Rectangle discardRewardBox = new Rectangle(200,280, Color.web("BD2D2D"));
        discardRewardBox.setArcHeight(10);
        discardRewardBox.setArcWidth(10);
        discardRewardBox.setOpacity(0.8);
        Text discardRewardText = new Text("+1 Discard");
//        Image discardRewardImage = new Image();
//        ImageView discardRewardImageView = new ImageView(discardRewardImage)
        discardRewardStackPane.getChildren().addAll(discardRewardBox, discardRewardText);

        StackPane moneyRewardStackPane = new StackPane();
        Rectangle moneyRewardBox = new Rectangle(200,280, Color.web("F9C91D"));
        moneyRewardBox.setArcHeight(10);
        moneyRewardBox.setArcWidth(10);
        moneyRewardBox.setOpacity(0.8);
        Text moneyRewardText = new Text("+1 Starting money");
//        Image moneyRewardImage = new Image();
//        ImageView moneyRewardImageView = new ImageView(moneyRewardImage)
        moneyRewardStackPane.getChildren().addAll(moneyRewardBox, moneyRewardText);

        StackPane incomeRewardStackPane = new StackPane();
        Rectangle incomeRewardBox = new Rectangle(200,280, Color.web("269D72"));
        incomeRewardBox.setArcHeight(10);
        incomeRewardBox.setArcWidth(10);
        incomeRewardBox.setOpacity(0.8);
        Text incomeRewardText = new Text("+1 Income");
//        Image incomeRewardImage = new Image();
//        ImageView incomeRewardImageView = new ImageView(incomeRewardImage)
        incomeRewardStackPane.getChildren().addAll(incomeRewardBox, incomeRewardText);


        rewardHBox.getChildren().addAll(handRewardStackPane, discardRewardStackPane, moneyRewardStackPane, incomeRewardStackPane);
        rewardVBox.getChildren().addAll(rewardText, rewardHBox);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(root,rewardFade,rewardVBox);
        rewardVBox.setAlignment(Pos.CENTER);
        rewardVBox.getStylesheets().add(getClass().getResource("/event.css").toExternalForm());
    }
}
