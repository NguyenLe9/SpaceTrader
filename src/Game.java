import java.util.Random;

public class Game {
    private Universe universe;
    private Player player;
    private String difficulty;
    private Random random;

    public Game(int[] skills, int credit, String difficulty) {
        this.universe = Universe.getUniverse();
        this.player = new Player(skills, credit, "Spock", universe.pickRandomRegion());
        this.setDifficulty(difficulty);
    }

    // public Game() {
    //     this.universe = Universe.getUniverse();
    //     this.Player = new Player();
    // }

    public double getDistance(Region destination) {
        return java.lang.Math.sqrt(Math.pow(this.player.getCurrReg().getX()
            - destination.getX(), 2) + Math.pow(this.player.getCurrReg().getY()
            - destination.getY(), 2));
    }

    public int getCost(Region destination) {
        int diffMult = calcDiffMult();
        return (int) ((getDistance(destination) * diffMult / 5)
            / Math.sqrt(1 + this.getPlayer().getpPoint()));
    }

    public boolean checkTravel(Region targetedRegion) {
        int targetedFuel = getCost(targetedRegion);
        int currentFuel = this.getPlayer().getShip().getFuel();
        return targetedFuel < currentFuel;
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
            itemList[i].setPrice((int) ((itemList[i].getPrice()
                * Math.pow(1.01, 16 - this.player.getmPoint()))
                / Math.log(itemList[i].getAmount() + 2)));
            inventory[i].setPrice((int) ((itemList[i].getPrice()
                * java.lang.Math.pow(0.99, 16 - this.player.getmPoint()))
                / Math.log(itemList[i].getAmount() + 2)));
        }
    }

    public NonPlayable randomEncounter() {
        int encounterRoll = random.nextInt(101);
        int diffMult = calcDiffMult();
        if (encounterRoll < 5) {
            return new Trader();
        } else if (encounterRoll - 5 < 5 * diffMult) {
            return new Bandit();
        } else if (encounterRoll - (5 * diffMult + 5) < 5 * diffMult) {
            return new Police();
        }
        return null;
    }

    public int calcDiffMult() {
        if (getDifficulty().equals("Easy")) {
            return 1;
        } else if (getDifficulty().equals("Normal")) {
            return 2;
        } else {
            return 3;
        }
    }

}
