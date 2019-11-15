public class Starship extends Ship {
    public Starship() {
        this.setCargo(200);
        this.setFuel(100);
        this.setHealth(150);
        super.setMax();
        setCargo(0);
    }
}