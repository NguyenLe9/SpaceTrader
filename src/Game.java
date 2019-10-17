// import java.lang.Math;

public class Game {
    private Universe universe;
    private Player player;
    private String difficulty;

    public Game(int[] skills, int credit, String difficulty) {
        this.universe = Universe.getUniverse();
        this.player = new Player(skills[0], skills[1], skills[2], skills[3],
            credit, universe.pickRandomRegion());
        // this.player = new Player(skills, credit, "", universe.pickRandomRegion());
        this.setDifficulty(difficulty);
    }
    public double getDistance(Region destination) {
        return java.lang.Math.sqrt(Math.pow(this.player.getCurrReg().getX()
            - destination.getX(), 2) + Math.pow(this.player.getCurrReg().getY()
            - destination.getY(), 2));
    }

    public double getCost(Region destination) {
        // implement a modifier to change cost depending on player's pilot skill
        int diffMult;
        if (getDifficulty().equals("Easy")) {
            diffMult = 1;
        } else if (getDifficulty().equals("Normal")) {
            diffMult = 2;
        } else {
            diffMult = 3;
        }
        return getDistance(destination) * diffMult / 10;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public Player getPlayer() {
        return this.player;
    }
    public String getDifficulty() {
        return this.difficulty;
    }
    public Universe getUniverse() {
        return this.universe;
    }

    public void calculateMarketPrice() {
        Item[] itemList = this.player.getCurrReg().getMarket().getItem();
        Item[] inventory = this.player.getShip().getInventory();
        for (int i = 0; i < itemList.length; i++) {
            itemList[i].setPrice((int) ((itemList[i].getPriceForCalc()
                * Math.pow(1.01, 16 - this.player.getmPoint()))
                / Math.log(1.1 + itemList[i].getAmount() * 0.03)));
            inventory[i].setPrice((int) ((itemList[i].getPriceForCalc()
                * java.lang.Math.pow(0.99, 16 - this.player.getmPoint()))
                / Math.log(1.1 + itemList[i].getAmount() * 0.03)));
        }
    }

}
