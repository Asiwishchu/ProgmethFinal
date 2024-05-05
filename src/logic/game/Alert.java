package logic.game;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Alert{
    private StackPane stackPane;
    private HBox root;
    private VBox alertSection;

    public Alert() {
        this.stackPane = new StackPane();
        this.root = new HBox(30);
        this.alertSection = new VBox(10);
    }

    // Initialize Alert message
    public void initializeAlert(String message, String color) {
        StackPane alertStackPane = new StackPane();
        Rectangle alertBox = new Rectangle(150, 40, Color.web(color));
        alertBox.setStrokeWidth(2);
        alertBox.setStroke(Color.SNOW);
        alertBox.setArcHeight(10);
        alertBox.setArcWidth(10);
        Text alertMessage = new Text(message);
        alertMessage.setId("alert-text");
        alertMessage.setFill(Color.WHITE);
        alertStackPane.getChildren().addAll(alertBox, alertMessage);

        alertSection.getChildren().add(alertStackPane);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(root, alertSection);

        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(0.2), alertStackPane);
        slideIn.setFromX(100);
        slideIn.setToX(0);


        PauseTransition pause = new PauseTransition(Duration.seconds(2));

        slideIn.setOnFinished(event -> pause.play());
        pause.setOnFinished(event -> {
            alertSection.getChildren().remove(alertStackPane);
            // Fade out transition for a smooth disappearing effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0), alertSection);
            fadeOut.setFromValue(1); // Start opaque
            fadeOut.setToValue(0); // Finish transparent
            fadeOut.setOnFinished(e -> {
                alertSection.getChildren().remove(alertStackPane);
                stackPane.getChildren().remove(alertSection);
                stackPane.getChildren().add(alertSection);
            });
            fadeOut.play();
        });
        slideIn.play();
    }// : Initialize Alert message

    public StackPane getStackPane() {
        return stackPane;
    }

    public HBox getRoot() {
        return root;
    }

    public VBox getAlertSection() {
        return alertSection;
    }
}
