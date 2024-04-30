package logic.card;

import application.Rank;
import application.Suit;
import javafx.scene.image.Image;
import logic.tarot.Nameable;


public class Card implements Nameable {
    private Suit suit;
    private Rank rank;
    private boolean isPlayed;

    private Image image;


//    public Card(Suit suit, Rank rank, String imagePath)
    public Card(Suit suit, Rank rank, String image) {
        this.suit = suit;
        this.rank = rank;
        this.isPlayed = false;
//        setImageByPath(imagePath);
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    @Override
    public String getName() {
        return this.rank + " Of " + this.suit;
    }
//    public void setImageByPath(String imagePath) {
//        String classloaderPath = ClassLoader.getSystemResource(imagePath).toString();
//        this.image = new Image(classloaderPath);}
//
//    public Image getImage() {
//        return image;
//    }

}
