package logic.game;

public class Blind {
    private int blindNo;
    private int reqScore;

    public Blind(int stageLv) {
        this.blindNo = stageLv;
        this.reqScore = Integer.MAX_VALUE;
    }

    public int getBlindNo() {
        return blindNo;
    }

    public void setBlindNo(int stageLv) {
        this.blindNo = stageLv;
    }


    //Reqiured Score Function
    public int getReqScore() {
        return reqScore = (int)Math.pow(GameController.getInstance().getBlind().getBlindNo(), 2) * 100 + 200;
    }

    public void setReqScore(int reqScore) {
        this.reqScore = reqScore;
    }
}
