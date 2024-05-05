package logic.tarot;

import application.AlertHandler;
import application.HandType;
import logic.game.Config;
import logic.game.GameController;

import java.util.Random;

public class WheelofFortune extends MediumCostTarot{
    AlertHandler alertHandler = GameController.getInstance().getAlertHandler();

    @Override
    public void useAbility() {
        Random random = new Random();
        switch(random.nextInt(7)){
            case 1: {
                alertHandler.initializeAlert("Mult /2", Config.RED);
                GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() / 2);
                System.out.println("mult/2");
            }
            case 2: {
                alertHandler.initializeAlert("Mult *2", Config.GREEN);
                GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() * 2);
                System.out.println("mult*2");
            }
            case 3: {
                alertHandler.initializeAlert("Chips /2", Config.RED);
                GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() / 2);
                System.out.println("chips/2");
            }
            case 4: {
                alertHandler.initializeAlert("Chips *2", Config.GREEN);
                GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() * 2);
                System.out.println("chips*2");
            }
            case 5: {
                alertHandler.initializeAlert("Money +6", Config.GREEN);
                GameController.getInstance().setMoney(GameController.getInstance().getMoney() + 6);
                System.out.println("money+6");
            }
            case 6: {
                alertHandler.initializeAlert("Score as HIGHCARD", Config.RED);
                GameController.getInstance().setCurrentHandType(HandType.HighCard);
                System.out.println("handtype=Highcard");
            }
            default: {
                alertHandler.initializeAlert("Mult +4", Config.GREEN);
                GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() + 4);
                System.out.println("mult +4");
            }
        }
    }

    @Override
    public String getDescription() {
        return "Give a random buff \n\n(+4 Mult, Mult/2, Mult x 2,Chips/2, Chips x 2, \n\n+6 Money, HandType = High Card)";
    }

    @Override
    public String getName() {
        return "Wheel of Fortune";
    }
}
