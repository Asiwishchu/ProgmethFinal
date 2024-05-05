package logic.tarot;

import javafx.scene.image.Image;

public abstract class Tarot implements Nameable, Descriptable, Imageable{
    private final int cost;

    public Tarot(int cost) {
        this.cost = cost;
    }

    public abstract void useAbility();

    public int getCost() {
        return cost;
    }

    @Override
    public Image getImage() {
        return new Image(ClassLoader.getSystemResource("TarotPic/"+ getName() + ".png").toString());
    }
}
