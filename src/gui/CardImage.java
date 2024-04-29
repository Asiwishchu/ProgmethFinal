package gui;

import javafx.scene.image.Image;

public class CardImage {

    private static final Image twoOfClubs = new Image(ClassLoader.getSystemResource("2c.png").toString());
    private static final Image threeOfClubs = new Image(ClassLoader.getSystemResource("3c.png").toString());
    private static final Image fourOfClubs = new Image(ClassLoader.getSystemResource("4c.png").toString());
    private static final Image fiveOfClubs = new Image(ClassLoader.getSystemResource("5c.png").toString());
    private static final Image sixOfClubs = new Image(ClassLoader.getSystemResource("6c.png").toString());
    private static final Image sevenOfClubs = new Image(ClassLoader.getSystemResource("7c.png").toString());
    private static final Image eightOfClubs = new Image(ClassLoader.getSystemResource("8c.png").toString());
    private static final Image nineOfClubs = new Image(ClassLoader.getSystemResource("9c.png").toString());
    private static final Image tenOfClubs = new Image(ClassLoader.getSystemResource("10c.png").toString());
    private static final Image jackOfClubs = new Image(ClassLoader.getSystemResource("Jc.png").toString());
    private static final Image queenOfClubs = new Image(ClassLoader.getSystemResource("Qc.png").toString());
    private static final Image kingOfClubs = new Image(ClassLoader.getSystemResource("Kc.png").toString());
    private static final Image aceOfClubs = new Image(ClassLoader.getSystemResource("Ac.png").toString());


    private static final Image twoOfDiamonds = new Image(ClassLoader.getSystemResource("2d.png").toString());
    private static final Image threeOfDiamonds = new Image(ClassLoader.getSystemResource("3d.png").toString());
    private static final Image fourOfDiamonds = new Image(ClassLoader.getSystemResource("4d.png").toString());
    private static final Image fiveOfDiamonds = new Image(ClassLoader.getSystemResource("5d.png").toString());
    private static final Image sixOfDiamonds = new Image(ClassLoader.getSystemResource("6d.png").toString());
    private static final Image sevenOfDiamonds = new Image(ClassLoader.getSystemResource("7d.png").toString());
    private static final Image eightOfDiamonds = new Image(ClassLoader.getSystemResource("8d.png").toString());
    private static final Image nineOfDiamonds = new Image(ClassLoader.getSystemResource("9d.png").toString());
    private static final Image tenOfDiamonds = new Image(ClassLoader.getSystemResource("10d.png").toString());
    private static final Image jackOfDiamonds = new Image(ClassLoader.getSystemResource("Jd.png").toString());
    private static final Image queenOfDiamonds= new Image(ClassLoader.getSystemResource("Qd.png").toString());
    private static final Image kingOfDiamonds = new Image(ClassLoader.getSystemResource("Kd.png").toString());
    private static final Image aceOfDiamonds = new Image(ClassLoader.getSystemResource("Ad.png").toString());


    private static final Image twoOfHearts = new Image(ClassLoader.getSystemResource("2h.png").toString());
    private static final Image threeOfHearts = new Image(ClassLoader.getSystemResource("3h.png").toString());
    private static final Image fourOfHearts = new Image(ClassLoader.getSystemResource("4h.png").toString());
    private static final Image fiveOfHearts = new Image(ClassLoader.getSystemResource("5h.png").toString());
    private static final Image sixOfHearts = new Image(ClassLoader.getSystemResource("6h.png").toString());
    private static final Image sevenOfHearts = new Image(ClassLoader.getSystemResource("7h.png").toString());
    private static final Image eightOfHearts = new Image(ClassLoader.getSystemResource("8h.png").toString());
    private static final Image nineOfHearts = new Image(ClassLoader.getSystemResource("9h.png").toString());
    private static final Image tenOfHearts = new Image(ClassLoader.getSystemResource("10h.png").toString());
    private static final Image jackOfHearts = new Image(ClassLoader.getSystemResource("Jh.png").toString());
    private static final Image queenOfHearts = new Image(ClassLoader.getSystemResource("Qh.png").toString());
    private static final Image kingOfHearts = new Image(ClassLoader.getSystemResource("Kh.png").toString());
    private static final Image aceOfHearts = new Image(ClassLoader.getSystemResource("Ah.png").toString());



    private static final Image twoOfSpades = new Image(ClassLoader.getSystemResource("2s.png").toString());
    private static final Image threeOfSpades = new Image(ClassLoader.getSystemResource("3s.png").toString());
    private static final Image fourOfSpades = new Image(ClassLoader.getSystemResource("4s.png").toString());
    private static final Image fiveOfSpades = new Image(ClassLoader.getSystemResource("5s.png").toString());
    private static final Image sixOfSpades = new Image(ClassLoader.getSystemResource("6s.png").toString());
    private static final Image sevenOfSpades = new Image(ClassLoader.getSystemResource("7s.png").toString());
    private static final Image eightOfSpades = new Image(ClassLoader.getSystemResource("8s.png").toString());
    private static final Image nineOfSpades = new Image(ClassLoader.getSystemResource("9s.png").toString());
    private static final Image tenOfSpades = new Image(ClassLoader.getSystemResource("10s.png").toString());
    private static final Image jackOfSpades = new Image(ClassLoader.getSystemResource("Js.png").toString());
    private static final Image queenOfSpades = new Image(ClassLoader.getSystemResource("Qs.png").toString());
    private static final Image kingOfSpades = new Image(ClassLoader.getSystemResource("Ks.png").toString());
    private static final Image aceOfSpades = new Image(ClassLoader.getSystemResource("As.png").toString());


    public static Image getCardImage(String card) {
        System.out.println(card);
        return switch (card){
            case "TWOofCLUBS" -> twoOfClubs;
            case "THREEofCLUBS" -> threeOfClubs;
            case "FOURofCLUBS" -> fourOfClubs;
            case "FIVEofCLUBS" -> fiveOfClubs;
            case "SIXofCLUBS" -> sixOfClubs;
            case "SEVENofCLUBS" -> sevenOfClubs;
            case "EIGHTofCLUBS" -> eightOfClubs;
            case "NINEofCLUBS" -> nineOfClubs;
            case "TENofCLUBS" -> tenOfClubs;
            case "JACKofCLUBS" -> jackOfClubs;
            case "QUEENofCLUBS" -> queenOfClubs;
            case "KINGofCLUBS" -> kingOfClubs;
            case "ACEofCLUBS" -> aceOfClubs;

            case "TWOofDIAMONDS" -> twoOfDiamonds;
            case "THREEofDIAMONDS" -> threeOfDiamonds;
            case "FOURofDIAMONDS" -> fourOfDiamonds;
            case "FIVEofDIAMONDS" -> fiveOfDiamonds;
            case "SIXofDIAMONDS" -> sixOfDiamonds;
            case "SEVENofDIAMONDS" -> sevenOfDiamonds;
            case "EIGHTofDIAMONDS" -> eightOfDiamonds;
            case "NINEofDIAMONDS" -> nineOfDiamonds;
            case "TENofDIAMONDS" -> tenOfDiamonds;
            case "JACKofDIAMONDS" -> jackOfDiamonds;
            case "QUEENofDIAMONDS" -> queenOfDiamonds;
            case "KINGofDIAMONDS" -> kingOfDiamonds;
            case "ACEofDIAMONDS" -> aceOfDiamonds;

            case "TWOofHEARTS" -> twoOfHearts;
            case "THREEofHEARTS" -> threeOfHearts;
            case "FOURofHEARTS" -> fourOfHearts;
            case "FIVEofHEARTS" -> fiveOfHearts;
            case "SIXofHEARTS" -> sixOfHearts;
            case "SEVENofHEARTS" -> sevenOfHearts;
            case "EIGHTofHEARTS" -> eightOfHearts;
            case "NINEofHEARTS" -> nineOfHearts;
            case "TENofHEARTS" -> tenOfHearts;
            case "JACKofHEARTS" -> jackOfHearts;
            case "QUEENofHEARTS" -> queenOfHearts;
            case "KINGofHEARTS" -> kingOfHearts;
            case "ACEofHEARTS" -> aceOfHearts;

            case "TWOofSPADES" -> twoOfSpades;
            case "THREEofSPADES" -> threeOfSpades;
            case "FOURofSPADES" -> fourOfSpades;
            case "FIVEofSPADES" -> fiveOfSpades;
            case "SIXofSPADES" -> sixOfSpades;
            case "SEVENofSPADES" -> sevenOfSpades;
            case "EIGHTofSPADES" -> eightOfSpades;
            case "NINEofSPADES" -> nineOfSpades;
            case "TENofSPADES" -> tenOfSpades;
            case "JACKofSPADES" -> jackOfSpades;
            case "QUEENofSPADES" -> queenOfSpades;
            case "KINGofSPADES" -> kingOfSpades;
            case "ACEofSPADES" -> aceOfSpades;

            default -> null;
        };


    }


}