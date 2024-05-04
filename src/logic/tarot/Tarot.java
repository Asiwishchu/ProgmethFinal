package logic.tarot;

import javafx.scene.image.Image;

public abstract class Tarot implements Nameable, Descriptable{
    private final int cost;
    private Image tarotImage;

    public Tarot(int cost) {
        this.cost = cost;
    }

    public abstract void useAbility();

    public int getCost() {
        return cost;
    }

    public Image getTarotImage(){
        return new Image(ClassLoader.getSystemResource("TarotPic/"+ getName() + ".png").toString());
    }
}
