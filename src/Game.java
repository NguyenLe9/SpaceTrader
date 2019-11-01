import java.util.Random;

public class Game {
    private Universe universe;
    private Player player;
    private String difficulty;
    private Random random = new Random();

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
            itemList[i].setPrice((int) ((itemList[i].getPriceForCalc()
                * Math.pow(1.01, 16 - this.player.getmPoint()))
                / Math.log(1.1 + itemList[i].getAmount() * 0.03)));
            inventory[i].setPrice((int) ((itemList[i].getPriceForCalc()
                * java.lang.Math.pow(0.99, 16 - this.player.getmPoint()))
                / Math.log(1.1 + itemList[i].getAmount() * 0.03)));
        }
    }

    public NonPlayable randomEncounter() {
        int encounterRoll = random.nextInt(101);
        int diffMult = calcDiffMult();
        if (encounterRoll < 5) {
            return new Trader();
        } else if (encounterRoll - 5 < 5 * diffMult) {
            return new Bandit();
        } else if (encounterRoll - (5 * diffMult + 5) < 5 * diffMult
                && this.player.getShip().getCargo() > 0) {
            return new Police(this.player.getShip().getInventory());
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

    public void robTrader() {
        if (random.nextInt(101) > 90 - 5 * this.player.getfPoint()) {
            // successful

            // TODO: wait for Trader implementation
            // for each item in Trader inventory, rng chance of obtaining (50/50)
        } else {
            // failure, penalty is 20 HP from ship
            this.player.getShip().changeHealth(-20);
        }
    }
    public void negotiateTrader() {
        double priceMod;
        if (random.nextInt(101) > 90 - 5 * this.player.getmPoint()) {
            priceMod = 0.5;
        } else {
            priceMod = 2;
        }
        // TODO: wait for Trader implementation
        // for each item in Trader inventory, apply priceMod
    }

    public void forfeitItemsPolice(Item[] demands) {
        for (int i = 0; i < this.player.getShip().getInventory().length; i++) {
            this.player.getShip().getInventory()[i].changeAmount(-demands[i].getAmount());
        }
    }

    public void fleePolice(Item[] demands, Region from, Region to) {
        if (random.nextInt(101) > 90 - 5 * this.player.getpPoint()) {
            // Ship ship = game.getPlayer().getShip();
            this.player.getShip().changeFuel(-getCost(to));
            this.player.setCurrReg(from);
        } else {
            forfeitItemsPolice(demands);
            this.player.getShip().changeHealth(-20);
            this.player.changeCredit((int) (-this.player.getCredit() * 0.2));
            this.player.setCurrReg(to);
        }
    }

    public void fightPolice(Item[] demands) {
        // only a failure state is needed for this one
        if (random.nextInt(101) < 90 - 5 * this.player.getfPoint()) {
            forfeitItemsPolice(demands);
            this.player.getShip().changeHealth(-60);
            this.player.changeCredit((int) (-this.player.getCredit() * 0.5));
        }
    }

    public void payBandit(int demand) {
        if (this.player.getCredit() > demand) {
            this.player.changeCredit(-demand);
        } else if (this.player.getShip().getCargo() > 0) {
            for (Item i: this.player.getShip().getInventory()) {
                i.setAmount(0);
            }
        } else {
            this.player.getShip().changeHealth(-60);
        }
    }
    public void fleeBandit(int demand, Region from, Region to) {
        if (random.nextInt(101) > 90 - 5 * this.player.getpPoint()) {
            // Ship ship = game.getPlayer().getShip();
            this.player.setCurrReg(from);
        } else {
            this.player.setCredit(0);
            this.player.getShip().changeHealth(-20);
            this.player.setCurrReg(to);
        }
    }

    public void fightBandit() {
        if (random.nextInt(101) > 90 - 5 * this.player.getfPoint()) {
            this.player.changeCredit(random.nextInt(3000));
        } else {
            this.player.setCredit(0);
            this.player.getShip().changeHealth(-60);
        }
    }
}
