package main;


import logic.game.GameController;

public class Main {

    public static void main(String[] args) {
        try {
            GameController gameInstance = GameController.getInstance();
            while(!gameInstance.isGameOver(gameInstance.getScore()))
                gameInstance.updateGameController();

        } catch (Exception ignored) {}

    }

}
