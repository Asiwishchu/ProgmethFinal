package main;


import javafx.application.Application;
import javafx.stage.Stage;
import logic.game.GameController;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {
        try {
            GameController gameInstance = GameController.getInstance();
            while(!gameInstance.isGameOver(gameInstance.getScore()))
                gameInstance.updateGameController();

        } catch (Exception ignored) {}

    }

}
