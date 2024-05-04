package logic.card;

import application.Rank;
import application.Suit;

public class MultCard extends Card{
    private int mult;

    public MultCard(Suit suit, Rank rank, int mult) {
        super(suit, rank);
        this.mult = mult;
    }

    public int getMult() {
        return mult;
    }

    public void setMult(int mult) {
        this.mult = mult;
    }
}
