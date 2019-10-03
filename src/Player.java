public class Player {
    private int pPoint;
    private int mPoint;
    private int ePoint;
    private int fPoint;
    private int credit;
    private Game.Region currentRegion;

    public void setpPoint(int pPoint) {
        this.pPoint = pPoint;
    }
    public void setmPoint(int mPoint) {
        this.mPoint = mPoint;
    }
    public void setfPoint(int fPoint) {
        this.fPoint = fPoint;
    }
    public void setePoint(int ePoint) {
        this.ePoint = ePoint;
    }
    public int getpPoint() {
        return this.pPoint;
    }
    public int getmPoint() {
        return this.mPoint;
    }
    public int getePoint() {
        return this.ePoint;
    }
    public int getfPoint() {
        return this.fPoint;
    }
    public void setCurrentRegion(Game.Region currentRegion) {
        this.currentRegion = currentRegion;
    }
    public Game.Region getCurrentRegion() {
        return this.currentRegion;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
    public int getCredit() {
        return this.credit;
    }

}
