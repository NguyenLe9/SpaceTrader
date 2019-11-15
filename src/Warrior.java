public class Warrior extends Ship {
    public Warrior() {
        this.setCargo(50);
        this.setFuel(200);
        this.setHealth(200);
        super.setMax();
        setCargo(0);
    }
}