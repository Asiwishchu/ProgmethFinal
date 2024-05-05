package logic.tarot;

import application.HandType;
import logic.game.GameController;

import java.util.Random;

public class WheelofFortune extends MediumCostTarot{

    @Override
    public void useAbility() {
        Random random = new Random();
        switch(random.nextInt(7)){
            case 1:
                GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() / 2);
            case 2:
                GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() * 2);
            case 3:
                GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() / 2);
            case 4:
                GameController.getInstance().setCurrentChips(GameController.getInstance().getCurrentChips() * 2);
            case 5:
                GameController.getInstance().setMoney(GameController.getInstance().getMoney() + 6);
            case 6:
                GameController.getInstance().setCurrentHandType(HandType.HighCard);
            default:
                GameController.getInstance().setCurrentMult(GameController.getInstance().getCurrentMult() + 4);
        }
    }

    @Override
    public String getDescription() {
        return "Give a random buff ( +4 Mult, Mult/2, Mult x 2,\n\nChips/2, Chips x 2, +6 Money,\n\n HandType = High Card)";
    }

    @Override
    public String getName() {
        return "Wheel of Fortune";
    }
}
