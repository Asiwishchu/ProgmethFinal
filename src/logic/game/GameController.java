package logic.game;

import application.HandType;
import logic.player.*;
import logic.tarot.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameController {
    private Player player;
    private Blind blind;

    private ArrayList<Tarot> tarotArrayList;
    private ArrayList<Tarot> selectedTarots = new ArrayList<>();

    //player resource
    private int playHand;
    private int discard;
    private int money;
    private int income;

    //Game setter
    private int handSizeReset = 0;
    private boolean theTowerSetter = false;

    //Game state
    private HandType currentHandType;
    private int currentChips;
    private int currentMult;

    private static GameController instance;

    public GameController(Player player, Blind blind) {
        this.player = player;
        this.blind = blind;
    }

    public static GameController getInstance(){
        if(instance == null)
            instance = new GameController(new Player(new Deck(), new Hand(Config.DefaultHandSize),0, Config.StartingMoney, Config.StartingIncome, Config.DefaultPlayRound, Config.DefaultDiscardRound), new Blind(1));
        return instance;
    }

    // Method to initialize and shuffle the deck
    public void initAndShuffleDeck(){
        player.getDeck().initDeck();
        player.getDeck().shuffleDeck();
    }

    public void refillTarots() {
        tarotArrayList = new ArrayList<>();
        Tarot[] newTarots = createNewTarot(Config.DefaultTarotListSize);
        Collections.addAll(tarotArrayList, newTarots);

    }


    public static Tarot[] createNewTarot(int size) {
        Random rand = new Random();
        ArrayList<Tarot> TarotList = new ArrayList<>(Arrays.asList(
                new TheFool(), new TheMagician(), new TheHighPriestess(), new TheEmpress(),
                new TheEmperor(), new TheHierophant(), new TheLovers(), new TheChariot(),
                new Strength(), new TheHermit(), new WheelofFortune(), new Justice(),
                new TheHangedMan(), new Death(), new Temperance(), new TheDevil(),
                new TheTower(), new TheStar(), new TheMoon(), new TheSun(),
                new Judgement(), new TheWorld()
        ));

        Tarot[] result = new Tarot[size];
        for (int i = 0; i < size; i++) {
            result[i] = TarotList.get(rand.nextInt(TarotList.size()));
        }

        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Blind getBlind() {
        return blind;
    }

    public void setBlind(Blind blind) {
        this.blind = blind;
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

    public int getHandSizeReset() {
        return Math.max(0 ,handSizeReset);
    }

    public void setHandSizeReset(int handSizeReset) {
        this.handSizeReset = Math.max(0 ,handSizeReset);
    }

    public int getCurrentChips() {
        return currentChips;
    }

    public void setCurrentChips(int currentChips) {
        this.currentChips = currentChips;
    }

    public int getCurrentMult() {
        return currentMult;
    }

    public void setCurrentMult(int currentMult) {
        this.currentMult = currentMult;
    }

    public ArrayList<Tarot> getSelectedTarots() {
        return selectedTarots;
    }

    public void setSelectedTarots(ArrayList<Tarot> selectedTarots) {
        this.selectedTarots = selectedTarots;
    }

    public HandType getCurrentHandType() {
        return currentHandType;
    }

    public void setCurrentHandType(HandType currentHandType) {
        this.currentHandType = currentHandType;
    }

    public boolean isTheTowerSetter() {
        return theTowerSetter;
    }

    public void setTheTowerSetter(boolean theTowerSetter) {
        this.theTowerSetter = theTowerSetter;
    }
}
