package logic.game;

import logic.card.Card;
import logic.player.*;

public class GameController {
    private Deck deck;
    private Hand hand;

    private static GameController instance;

    public GameController(){
        initAndShuffleDeck();
        initPlayer();


        deck = new Deck();
        hand = new Hand(hand.getHandSize());
    }

    public static GameController getInstance(){
        if(instance == null)
            instance = new GameController();
        return instance;
    }

    // Method to initialize and shuffle the deck
    public void initAndShuffleDeck(){
        deck.initDeck();
        deck.shuffleDeck();
    }

    public void initPlayer(){
        hand.initHand();
    }

    // Method to draw a card from the deck
    public Card drawCardFromDeck(){
        return deck.drawCard();
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
