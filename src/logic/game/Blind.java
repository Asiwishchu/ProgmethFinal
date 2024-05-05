package logic.game;

public class Blind {
    private int blindNo;
    private int reqScore;

    public Blind(int blindNo) {
        this.blindNo = blindNo;
        this.reqScore = Integer.MAX_VALUE;
    }

    public int getBlindNo() {
        return Math.max(1, blindNo);
    }

    public void setBlindNo(int blindNo) {
        this.blindNo = Math.max(1, blindNo);
    }


    //Required Score Function
    public int getReqScore() {
        return reqScore = (int)Math.pow(GameController.getInstance().getBlind().getBlindNo(), 2) * 100 + 200;
    }

    public void setReqScore(int reqScore) {
        this.reqScore = reqScore;
    }
}
