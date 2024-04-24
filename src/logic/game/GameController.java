package logic.game;

import application.HandType;
import application.Rank;
import application.Suit;
import logic.card.Card;
import logic.player.*;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private Player player;
    private Stage stage;

    private static GameController instance;

    public GameController(Player player, Stage stage) {
        this.player = player;
        this.stage = stage;
    }

    public static GameController getInstance(){
        if(instance == null)
            instance = new GameController(new Player(new Deck(), new Hand(7),0,0 ,4,2 ), new Stage(1));
        return instance;
    }

    // Method to initialize and shuffle the deck
    public void initAndShuffleDeck(){
        player.getDeck().initDeck();
        player.getDeck().shuffleDeck();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
