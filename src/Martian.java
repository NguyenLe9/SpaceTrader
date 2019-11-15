public class Martian extends Ship {
    public Martian() {
        this.setCargo(150);
        this.setFuel(200);
        this.setHealth(100);
        super.setMax();
        setCargo(0);
    }
}