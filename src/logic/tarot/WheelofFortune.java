package logic.tarot;

import application.HandType;
import logic.game.Config;
import logic.game.GameController;

import java.util.Random;

public class WheelofFortune extends MediumCostTarot{

    @Override
    public void useAbility() {
        Random random = new Random();
        switch(random.nextInt(7)){
            case 1: {
                GameController.getInstance().getAlert().initializeAlert("Mult /2", Config.RED);
                GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() / 2);
                System.out.println("mult/2");
            }
            case 2: {
                GameController.getInstance().getAlert().initializeAlert("Mult *2", Config.GREEN);
                GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() * 2);
                System.out.println("mult*2");
            }
            case 3: {
                GameController.getInstance().getAlert().initializeAlert("Chips /2", Config.RED);
                GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() / 2);
                System.out.println("chips/2");
            }
            case 4: {
                GameController.getInstance().getAlert().initializeAlert("Chips *2", Config.GREEN);
                GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() * 2);
                System.out.println("chips*2");
            }
            case 5: {
                GameController.getInstance().getAlert().initializeAlert("Money +6", Config.GREEN);
                GameController.getInstance().setMoney(GameController.getInstance().getMoney() + 6);
                System.out.println("money+6");
            }
            case 6: {
                GameController.getInstance().getAlert().initializeAlert("Score as HIGHCARD", Config.RED);
                GameController.getInstance().setCurrentHandType(HandType.HighCard);
                System.out.println("handtype=Highcard");
            }
            default: {
                GameController.getInstance().getAlert().initializeAlert("Mult +4", Config.GREEN);
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
