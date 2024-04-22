package logic.game;

import logic.card.Card;
import logic.player.*;

public class GameController {
    private Deck deck;
    private Hand hand;
    private int score;
    private Stage stage;

    private static GameController instance;

    public GameController(Deck deck, Hand hand, int score, Stage stage) {
        this.deck = deck;
        this.hand = hand;
        this.score = score;
        this.stage = stage;
    }

    public static GameController getInstance(){
        if(instance == null)
            instance = new GameController(new Deck(), new Hand(7), 0, new Stage(1));
        return instance;
    }

    public void updateGameController(){

        isGameOver(this.score);//check score > req score?
        this.getStage().setStageLv(getStage().getStageLv()+1);//stage level +1
    }

    // Method to initialize and shuffle the deck
    public void initAndShuffleDeck(){
        deck.initDeck();
        deck.shuffleDeck();
    }

    public void initPlayer(){
        hand.initHand();
    }

    public boolean isGameOver(int score){
        return score < stage.getReqScore();
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
