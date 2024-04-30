package gui;

import application.Rank;
import application.Suit;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import logic.card.Card;

import java.util.ArrayList;

public class CardPane extends HBox {

    private static CardPane instance;
    private ArrayList<Card> cardsPic;

    private CardPane(){
        cardsPic =new ArrayList<>();

        cardsPic.add(new Card(Suit.CLUBS, Rank.TWO, "2c.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.THREE, "3c.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.FOUR, "4c.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.FIVE, "5c.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.SIX, "6c.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.SEVEN, "7c.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.EIGHT, "8c.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.NINE, "9c.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.TEN, "10c.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.JACK, "Jc.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.QUEEN, "Qc.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.KING, "Kc.png"));
        cardsPic.add(new Card(Suit.CLUBS, Rank.ACE, "Ac.png"));

        cardsPic.add(new Card(Suit.DIAMONDS, Rank.TWO, "2d.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.THREE, "3d.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.FOUR, "4d.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.FIVE, "5d.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.SIX, "6d.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.SEVEN, "7d.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.EIGHT, "8d.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.NINE, "9d.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.TEN, "10d.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.JACK, "Jd.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.QUEEN, "Qd.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.KING, "Kd.png"));
        cardsPic.add(new Card(Suit.DIAMONDS, Rank.ACE, "Ad.png"));

        cardsPic.add(new Card(Suit.HEARTS, Rank.TWO, "2h.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.THREE, "3h.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.FOUR, "4h.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.FIVE, "5h.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.SIX, "6h.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.SEVEN, "7h.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.EIGHT, "8h.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.NINE, "9h.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.TEN, "10h.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.JACK, "Jh.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.QUEEN, "Qh.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.KING, "Kh.png"));
        cardsPic.add(new Card(Suit.HEARTS, Rank.ACE, "Ah.png"));

        cardsPic.add(new Card(Suit.SPADES, Rank.TWO, "2s.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.THREE, "3s.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.FOUR, "4s.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.FIVE, "5s.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.SIX, "6s.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.SEVEN, "7s.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.EIGHT, "8s.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.NINE, "9s.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.TEN, "10s.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.JACK, "Js.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.QUEEN, "Qs.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.KING, "Ks.png"));
        cardsPic.add(new Card(Suit.SPADES, Rank.ACE, "As.png"));

        this.setAlignment(Pos.CENTER);
    }

    public static CardPane getInstance() {
        if (instance == null) {
            instance = new CardPane();
        }
        return instance;
    }

    public ArrayList<Card> getCards() {
        return cardsPic;
    }


}
