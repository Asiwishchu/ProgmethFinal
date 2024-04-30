package logic.game;

import logic.player.*;
import logic.tarot.Tarot;
import logic.tarot.TheFool;
import logic.tarot.TheHangedMan;
import logic.tarot.TheHermit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameController {
    private Player player;
    private Stage stage;
    private ArrayList<Tarot> tarotArrayList;
    private int playHand;
    private int discard;
    private int money;
    private int income;

    private static GameController instance;

    public GameController(Player player, Stage stage) {
        this.player = player;
        this.stage = stage;
    }

    public static GameController getInstance(){
        if(instance == null)
            instance = new GameController(new Player(new Deck(), new Hand(Config.DefaultHandSize),0, Config.StartingMoney, Config.StartingIncome, Config.DefaultPlayRound, Config.DefaultDiscardRound), new Stage(1));
        return instance;
    }

    // Method to initialize and shuffle the deck
    public void initAndShuffleDeck(){
        player.getDeck().initDeck();
        player.getDeck().shuffleDeck();
    }

    public void refillTarots(){
        tarotArrayList = new ArrayList<>();
        while(tarotArrayList.size() < Config.DefauultTarotListSize){
            tarotArrayList.add(createNewTarot());
        }
    }

    public static Tarot createNewTarot() {
        Random rand = new Random();
        ArrayList<Tarot> TarotList = new ArrayList<Tarot>(Arrays.asList(new TheFool(), new TheHangedMan(), new TheHermit()));

        return TarotList.get(rand.nextInt(TarotList.size()));
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getIncome() {
        return Math.max(0 ,income);
    }

    public void setIncome(int income) {
        this.income = Math.max(0 ,income);
    }

    public int getMoney() {
        return Math.max(0 ,money);
    }

    public void setMoney(int money) {
        this.money = Math.max(0 ,money);
    }

    public int getPlayHand() {
        return Math.max(0 ,playHand);
    }

    public void setPlayHand(int playHand) {
        this.playHand = Math.max(0 ,playHand);
    }

    public int getDiscard() {
        return Math.max(0 ,discard);
    }

    public void setDiscard(int discard) {
        this.discard = Math.max(0 ,discard);
    }

    public ArrayList<Tarot> getTarotArrayList() {
        return tarotArrayList;
    }

    public void setTarotArrayList(ArrayList<Tarot> tarotArrayList) {
        this.tarotArrayList = tarotArrayList;
    }
}
