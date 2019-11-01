public class Trader implements NonPlayable {
    private Market market;
    private Region region;
    private Item offered; // the item to offer
    private int price; // new price
    private int damage;
    private String speak;
    private boolean sold;

    // Trader takes the Market of the Region they're in
    public Trader(Region region) {
        this.region = region;
        this.market = region.getMarket();
        int itemIndex = (int) (Math.random() * (market.getItem().length));
        this.offered = market.getItem()[itemIndex]; // get a random item
        this.price = offered.getPrice();
        this.damage = 0;
        this.speak = "\"Got some rare things on sale, stranger!\" (Trader wants to sell " + offered.getName() + " for " + price + ")";
        this.sold = false;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void setPrice(int price) {
        this.price = price;
    } // for negotiate

    public Item getOffered() {
        return this.offered;
    }

    public int getPrice() {
        return this.price;
    }

    public int getDamage() {
        return this.damage;
    }

    public void makeOffer() {
        this.price = offered.getPrice();
    } // do some math to make a better offer?

    // If the player fails, the trader will damage the player's ship health.
    public void attack(Player player) {
        this.damage = 18 - player.getfPoint(); // the higher the Fighter skill,
        // the lower the damage. change the math.
        int newHealth = player.getShip().getHealth() - damage;
        player.getShip().setHealth(newHealth);
    }

    public void setSold(boolean b) {
        this.sold = b;
    }

    public boolean getSold() {
        return this.sold;
    }

    public void setSpeak(String s) {
        this.speak = s;
    }

    public String getSpeak() {
        return this.speak;
    }

    public String getImageName() {
        return "Graphics/trader.jpg";
    }
}