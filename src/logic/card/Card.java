package logic.card;

import application.Rank;
import application.Suit;
import javafx.scene.image.Image;
import logic.tarot.Imageable;
import logic.tarot.Nameable;


public class Card implements Nameable, Imageable {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String getName() {
        return this.rank + " Of " + this.suit;
    }

    @Override
    public Image getImage() {
        String cardRank;
        String cardSuit;

        switch (getRank()){
            case ACE -> cardRank = "A";
            case TWO -> cardRank = "2";
            case THREE -> cardRank = "3";
            case FOUR -> cardRank = "4";
            case FIVE -> cardRank = "5";
            case SIX -> cardRank = "6";
            case SEVEN -> cardRank = "7";
            case EIGHT -> cardRank = "8";
            case NINE -> cardRank = "9";
            case TEN -> cardRank = "10";
            case JACK -> cardRank = "J";
            case QUEEN -> cardRank = "Q";
            case KING -> cardRank = "K";
            case null, default -> cardRank = "";
        }

        switch (getSuit()){
            case CLUBS -> cardSuit = "c";
            case DIAMONDS -> cardSuit = "d";
            case HEARTS -> cardSuit = "h";
            case SPADES -> cardSuit = "s";
            case null, default -> cardSuit = "";
        }

        return new Image(ClassLoader.getSystemResource("CardPic/" + cardRank + cardSuit +".png").toString());
    };
}
