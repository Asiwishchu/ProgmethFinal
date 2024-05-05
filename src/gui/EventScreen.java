package gui;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;
import logic.game.GameController;


public class EventScreen {
    public void showWinningScreen(StackPane stackPane, HBox root, SideBar mySideBar){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Rectangle winningFade = new Rectangle(screenBounds.getWidth(),screenBounds.getHeight());
        winningFade.setOpacity(0.8);
        Rectangle winningStripe = new Rectangle(screenBounds.getWidth(), 200, Color.web("41454A"));
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


        fadeIn.play();
        fadeIn.setOnFinished(e -> pause.play());
        pause.setOnFinished(e -> {
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
            showRewardScreen(stackPane, root, mySideBar);
        });
    }

    public void showLosingScreen(StackPane stackPane, HBox root, SideBar mySideBar){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Rectangle losingFade = new Rectangle(screenBounds.getWidth(),screenBounds.getHeight(),Color.BLACK);
        losingFade.setOpacity(0.9);
        VBox losingVBox = new VBox(30);
        Text gameOverText = new Text("Game Over");
        gameOverText.setId("game-over-title");
        Text totalScoreText = new Text("Total Score");
        totalScoreText.setId("game-over-sub-title");
        Text totalScoreAmount = new Text(GameController.getInstance().getTotalScore() + "");
        totalScoreAmount.setId("game-over-sub-title");
        Button retryButton = new Button("Retry?");
        retryButton.setOnAction(event -> {
            GameController.getInstance().resetGame();
            mySideBar.updateSideBar();
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

    public void showRewardScreen(StackPane stackPane, HBox root, SideBar mySideBar){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Rectangle rewardFade = new Rectangle(screenBounds.getWidth(),screenBounds.getHeight(),Color.BLACK);
        rewardFade.setOpacity(0.9);
        VBox rewardVBox = new VBox(30);
        rewardVBox.setAlignment(Pos.CENTER);
        Text rewardText = new Text("Choose your reward");
        rewardText.setId("game-over-title");
        HBox rewardHBox = new HBox(20);
        rewardHBox.setAlignment(Pos.CENTER);



        Image handRewardImage = new Image("/RewardPic/hand.png");
        ImageView handRewardImageView = new ImageView(handRewardImage);
        handRewardImageView.setFitWidth(190);
        handRewardImageView.setFitHeight(240);
        ScaleTransition handScaleIn = new ScaleTransition(Duration.millis(200), handRewardImageView);
        handScaleIn.setToX(1.1);
        handScaleIn.setToY(1.1);
        ScaleTransition handScaleOut = new ScaleTransition(Duration.millis(200), handRewardImageView);
        handScaleOut.setToX(1);
        handScaleOut.setToY(1);
        handRewardImageView.setOnMouseEntered(e -> {
            handScaleIn.play();
        });
        handRewardImageView.setOnMouseExited(e -> {
            handScaleOut.play();
        });
        handRewardImageView.setOnMouseClicked(e -> {
            GameController.getInstance().getPlayer().setPlayRound(GameController.getInstance().getPlayer().getPlayRound() + 1);
            GameController.getInstance().setPlayHand(GameController.getInstance().getPlayer().getPlayRound());
            mySideBar.updateSideBar();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
        });


        Image discardRewardImage = new Image("/RewardPic/discard.png");
        ImageView discardRewardImageView = new ImageView(discardRewardImage);
        discardRewardImageView.setFitWidth(190);
        discardRewardImageView.setFitHeight(240);
        ScaleTransition discardScaleIn = new ScaleTransition(Duration.millis(200), discardRewardImageView);
        discardScaleIn.setToX(1.1);
        discardScaleIn.setToY(1.1);
        ScaleTransition discardScaleOut = new ScaleTransition(Duration.millis(200), discardRewardImageView);
        discardScaleOut.setToX(1);
        discardScaleOut.setToY(1);
        discardRewardImageView.setOnMouseEntered(e -> {
            discardScaleIn.play();
        });
        discardRewardImageView.setOnMouseExited(e -> {
            discardScaleOut.play();
        });
        discardRewardImageView.setOnMouseClicked(e -> {
            GameController.getInstance().getPlayer().setDiscardRound(GameController.getInstance().getPlayer().getDiscardRound() + 1);
            GameController.getInstance().setDiscard(GameController.getInstance().getPlayer().getDiscardRound());
            mySideBar.updateSideBar();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
        });


        Image moneyRewardImage = new Image("/RewardPic/startmoney.png");
        ImageView moneyRewardImageView = new ImageView(moneyRewardImage);
        moneyRewardImageView.setFitWidth(190);
        moneyRewardImageView.setFitHeight(240);
        ScaleTransition moneyScaleIn = new ScaleTransition(Duration.millis(200), moneyRewardImageView);
        moneyScaleIn.setToX(1.1);
        moneyScaleIn.setToY(1.1);
        ScaleTransition moneyScaleOut = new ScaleTransition(Duration.millis(200), moneyRewardImageView);
        moneyScaleOut.setToX(1);
        moneyScaleOut.setToY(1);
        moneyRewardImageView.setOnMouseEntered(e -> {
            moneyScaleIn.play();
        });
        moneyRewardImageView.setOnMouseExited(e -> {
            moneyScaleOut.play();
        });
        moneyRewardImageView.setOnMouseClicked(e -> {
            GameController.getInstance().getPlayer().setStartingMoney(GameController.getInstance().getPlayer().getStartingMoney() + 5);
            GameController.getInstance().setMoney(GameController.getInstance().getPlayer().getStartingMoney());
            mySideBar.updateSideBar();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
        });

        Image incomeRewardImage = new Image("/RewardPic/income.png");
        ImageView incomeRewardImageView = new ImageView(incomeRewardImage);
        incomeRewardImageView.setFitWidth(190);
        incomeRewardImageView.setFitHeight(240);
        ScaleTransition incomeScaleIn = new ScaleTransition(Duration.millis(200), incomeRewardImageView);
        incomeScaleIn.setToX(1.1);
        incomeScaleIn.setToY(1.1);
        ScaleTransition incomeScaleOut = new ScaleTransition(Duration.millis(200), incomeRewardImageView);
        incomeScaleOut.setToX(1);
        incomeScaleOut.setToY(1);
        incomeRewardImageView.setOnMouseEntered(e -> {
            incomeScaleIn.play();
        });
        incomeRewardImageView.setOnMouseExited(e -> {
            incomeScaleOut.play();
        });
        incomeRewardImageView.setOnMouseClicked(e -> {
            GameController.getInstance().getPlayer().setStartingIncome(GameController.getInstance().getPlayer().getStartingIncome() + 2);
            GameController.getInstance().setIncome(GameController.getInstance().getPlayer().getStartingIncome());
            mySideBar.updateSideBar();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(root);
        });



        rewardHBox.getChildren().addAll(handRewardImageView, discardRewardImageView, moneyRewardImageView, incomeRewardImageView);
        rewardVBox.getChildren().addAll(rewardText, rewardHBox);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(root,rewardFade,rewardVBox);
        rewardVBox.setAlignment(Pos.CENTER);
        rewardVBox.getStylesheets().add(getClass().getResource("/event.css").toExternalForm());
    }
}
