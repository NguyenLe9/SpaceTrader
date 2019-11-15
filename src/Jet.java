public class Jet extends Ship {
    public Jet() {
        this.setCargo(100);
        this.setFuel(200);
        this.setHealth(150);
        super.setMax();
        setCargo(0);
    }
}