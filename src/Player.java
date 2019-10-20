public class Player {
    private int pPoint;
    private int fPoint;
    private int mPoint;
    private int ePoint;
    // private int[] skills;
    private Ship ship;
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
        this.ship = new Ship("Starship");
        this.currentRegion = currentRegion;
    }
    // public Player(int[] skills, int credit, String name, Region currentRegion) {
    //     this.skills = skills;
    //     this.credit = credit;
    //     this.name = name;
    //     this.currentRegion = currentRegion;
    // }

    public void setpPoint(int pPoint) {
        this.pPoint = pPoint;
        // this.skills[0] = pPoint;
    }
    public void setfPoint(int fPoint) {
        this.fPoint = fPoint;
        // this.skills[1] = fPoint;
    }
    public void setmPoint(int mPoint) {
        this.mPoint = mPoint;
        // this.skills[2] = mPoint;
    }
    public void setePoint(int ePoint) {
        this.ePoint = ePoint;
        // this.skills[3] = ePoint
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCurrReg(Region currentRegion) {
        this.currentRegion = currentRegion;
    }
    public int getpPoint() {
        return this.pPoint;
        // return this.skills[0];
    }
    public int getfPoint() {
        return this.fPoint;
        // return this.skills[1];
    }
    public int getmPoint() {
        return this.mPoint;
        // return this.skills[2];
    }
    public int getePoint() {
        return this.ePoint;
        // return this.skills[3];
    }
    public int getCredit() {
        return this.credit;
    }
    public String getName() {
        return this.name;
    }
    public Region getCurrReg() {
        return this.currentRegion;
    }
    public Ship getShip() {
        return this.ship;
    }

    public void changeCredit(int credit) {
        this.credit += credit;
    }
    public boolean checkTravel(Region targetedRegion) {
        boolean isSufficient = true;
        int targetedFuel =
	    this.currentRegion.calculateFuelCost(targetedRegion, pPoint);
        int currentFuel = this.ship.getFuel();
        if (targetedFuel > currentFuel) {
            isSufficient = false;
        }
        return isSufficient;
    }
}
