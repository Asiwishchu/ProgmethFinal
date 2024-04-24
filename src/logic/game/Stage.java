package logic.game;

public class Stage {
    private int stageLv;
    private int reqScore;

    public Stage(int stageLv) {
        this.stageLv = stageLv;
        this.reqScore = 300;
    }

    public int getStageLv() {
        return stageLv;
    }

    public void setStageLv(int stageLv) {
        this.stageLv = stageLv;
    }


    //Reqiured Score Function
    public int getReqScore() {
        return reqScore = (int)Math.pow(GameController.getInstance().getStage().getStageLv(), 2) * 100 + 200;
    }

    public void setReqScore(int reqScore) {
        this.reqScore = reqScore;
    }
}
