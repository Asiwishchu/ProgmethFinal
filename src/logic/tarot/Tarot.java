package logic.tarot;

import logic.player.Player;

import java.awt.*;

public abstract class Tarot implements Nameable, Descriptable{
    private int cost;
    private Image tarotImage;

    public Tarot(int cost) {
        this.cost = cost;
    }

    public abstract void useAbility();

    public int getCost() {
        return cost;
    }
}
