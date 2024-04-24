package logic.player;

import logic.game.Stage;

public class Player {
    private Deck deck;
    private Hand hand;
    private int score;
    private int money;
    private int playRound;
    private int discardRound;

    public Player(Deck deck, Hand hand, int score, int money, int playRound, int discardRound) {
        this.deck = deck;
        this.hand = hand;
        this.score = score;
        this.money = money;
        this.playRound = playRound;
        this.discardRound = discardRound;
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
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPlayRound() {
        return playRound;
    }

    public void setPlayRound(int playRound) {
        this.playRound = playRound;
    }

    public int getDiscardRound() {
        return discardRound;
    }

    public void setDiscardRound(int discardRound) {
        this.discardRound = discardRound;
    }

}
