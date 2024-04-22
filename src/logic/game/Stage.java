package logic.game;

public class Stage {
    private int stageLv;
    private int reqScore;

    public Stage(int stageLv) {
        this.stageLv = stageLv;
        this.reqScore = (stageLv^2 * 100) + 200;
    }

    public int getStageLv() {
        return stageLv;
    }

    public void setStageLv(int stageLv) {
        this.stageLv = stageLv;
    }

    public int getReqScore() {
        return reqScore;
    }

    public void setReqScore(int reqScore) {
        this.reqScore = reqScore;
    }
}
