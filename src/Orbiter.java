public class Orbiter extends Ship {
    public Orbiter() {
        this.setCargo(100);
        this.setFuel(100);
        this.setHealth(100);
        super.setMax();
        setCargo(0);
    }
}