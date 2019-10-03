public class Player {
    private int pPoint;
    private int fPoint;
    private int mPoint;
    private int ePoint;
    private int credit;
    private String name;
    private Region currentRegion;

    public Player(int pPoint, int fPoint, int mPoint, int ePoint, int credit,
            Region currentRegion) {
        this.pPoint = pPoint;
        this.fPoint = fPoint;
        this.mPoint = mPoint;
        this.ePoint = ePoint;
        this.credit = credit;
        this.currentRegion = currentRegion;
    }

    public void setpPoint(int pPoint) {
        this.pPoint = pPoint;
    }
    public void setfPoint(int fPoint) {
        this.fPoint = fPoint;
    }
    public void setmPoint(int mPoint) {
        this.mPoint = mPoint;
    }
    public void setePoint(int ePoint) {
        this.ePoint = ePoint;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCurrentRegion(Region currentRegion) {
        this.currentRegion = currentRegion;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
    public int getpPoint() {
        return this.pPoint;
    }
    public int getfPoint() {
        return this.fPoint;
    }
    public int getmPoint() {
        return this.mPoint;
    }
    public int getePoint() {
        return this.ePoint;
    }
    public Region getCurrentRegion() {
        return this.currentRegion;
    }
    public int getCredit() {
        return this.credit;
    }
    public String getName() {
        return this.name;
    }


}
