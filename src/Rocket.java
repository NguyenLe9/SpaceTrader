public class Rocket extends Ship {
    public Rocket() {
        this.setCargo(50);
        this.setFuel(200);
        this.setHealth(100);
        super.setMax();
        setCargo(0);
    }
}