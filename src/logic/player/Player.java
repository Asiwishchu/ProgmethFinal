package logic.player;

import logic.game.Stage;
import logic.tarot.Tarot;

import java.util.ArrayList;

public class Player {
    private Deck deck;
    private Hand hand;
    private int score;
    private int startingMoney;
    private int startingIncome;
    private int playRound;
    private int discardRound;

    public Player(Deck deck, Hand hand, int score, int startingMoney, int startingIncome, int playRound, int discardRound) {
        this.deck = deck;
        this.hand = hand;
        this.score = Math.max(0, score);
        this.startingMoney = Math.max(0, startingMoney);
        this.startingIncome = Math.max(0, startingIncome);
        this.playRound = Math.max(0, playRound);
        this.discardRound = Math.max(0, discardRound);
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

    public int getScore() {
        return Math.max(0, score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStartingMoney() {
        return Math.max(0, startingMoney);
    }

    public void setStartingMoney(int startingMoney) {
        this.startingMoney = startingMoney;
    }

    public int getPlayRound() {
        return Math.max(0, playRound);
    }

    public void setPlayRound(int playRound) {
        this.playRound = playRound;
    }

    public int getDiscardRound() {
        return Math.max(0, discardRound);
    }

    public void setDiscardRound(int discardRound) {
        this.discardRound = discardRound;
    }

    public int getStartingIncome() {
        return Math.max(0 ,startingIncome);
    }

    public void setStartingIncome(int startingIncome) {
        this.startingIncome = startingIncome;
    }
}
