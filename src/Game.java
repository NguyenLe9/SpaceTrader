import java.util.Random;

public class Game {
    private Universe universe;
    private Player player;
    private String difficulty;
    private Random random = new Random();
    private static int karma = 0;

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
    public int getKarma() {
        return karma;
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
            return new Trader(player.getCurrReg());
        } else if (encounterRoll - 5 < 5 * diffMult) {
            return new Bandit((int) (this.player.getCredit() * random.nextDouble()));
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

    public void buyTrader(Trader trader) {
        if (trader.getPrice() < this.player.getCredit()) {
            for (Item i: this.player.getShip().getInventory()) {
                if (trader.getOffered().getName().equals(i.getName())) {
                    i.changeAmount(1);
                }
            }
            this.player.changeCredit(-trader.getPrice());
            trader.setSpeak("\"Heh heh heh... Thank you!\"");
            trader.setSold(true);
        } else {
            trader.setSpeak("\"Not enough cash, stranger!\"");
        }
        this.player.getShip().changeCargo(1);

    }

    public void robTrader(Trader trader) {
        if (random.nextInt(101) > 90 - 5 * this.player.getfPoint()) {
            for (Item i: this.player.getShip().getInventory()) {
                if (trader.getOffered().getName().equals(i.getName())) {
                    i.changeAmount(1);
                }
            }
            trader.setSpeak("Successfully robbed the trader.");
            this.player.getShip().changeCargo(1);
        } else {
            this.player.getShip().changeHealth(-20);
            trader.setSpeak("Unable to rob the trader. Ship was damaged.");
        }
        karma++;
    }
    public void negotiateTrader(Trader trader) {
        if (random.nextInt(101) > 90 - 5 * this.player.getmPoint()) {
            trader.setPrice(trader.getPrice() / 2);
            trader.setSpeak("Negotiation successful. The price is now " + trader.getPrice() + ".");
        } else {
            trader.setPrice(trader.getPrice() * 2);
            trader.setSpeak("Negotiation failed. The price is now " + trader.getPrice() + ".");
        }
    }

    public void forfeitItemsPolice(Police police) {
        for (int i = 0; i < police.getSuspected().length; i++) {
            for (int j = 0; j < this.player.getShip().getInventory().length; j++) {
                if (this.player.getShip().getInventory()[j].getName()
                        .equals(police.getSuspected()[i].getName())) {
                    this.player.getShip().getInventory()[j].changeAmount(-1);
                }
            }
        }
        police.setSpeak("\"Thank you for your cooperation.\"");
        this.player.getShip().changeCargo(-police.getSuspected().length);
        karma--;
    }

    public void fleePolice(Police police, Region from) {
        this.player.setCurrReg(from);
        if (random.nextInt(101) > 90 - 5 * this.player.getpPoint()) {
            police.setSpeak("Successfully escaped.");
        } else {
            forfeitItemsPolice(police);
            this.player.getShip().changeHealth(-20);
            this.player.changeCredit((int) (-this.player.getCredit() * 0.3));
            police.setSpeak("Unable to escape. Goods were confiscated and was fined "
                + (this.player.getCredit() * 0.3) + ".");
        }
        karma++;
    }

    public void fightPolice(Police police) {
        if (random.nextInt(101) > 90 - 5 * this.player.getfPoint()) {
            police.setSpeak("Fought off the police.");
        } else {
            forfeitItemsPolice(police);
            this.player.getShip().changeHealth(-60);
            this.player.changeCredit((int) (-this.player.getCredit() * 0.5));
            police.setSpeak("Defeated by the police. The ship was damaged and was fined "
                + (this.player.getCredit() * 0.5) + ".");
        }
        karma++;
    }

    public void payBandit(Bandit bandit) {
        if (this.player.getCredit() > bandit.getLoot()) {
            this.player.changeCredit(-bandit.getLoot());
            bandit.setSpeak("Paid the bandit.");
        } else if (this.player.getShip().getCargo() > 0) {
            for (Item i: this.player.getShip().getInventory()) {
                i.setAmount(0);
            }
            bandit.setSpeak("Unable to pay the bandit. They took some supplies instead.");
            this.player.getShip().setCargo(0);
        } else {
            this.player.getShip().changeHealth(-60);
            bandit.setSpeak("Unable to pay the bandit. They ransacked the ship.");
            this.player.getShip().setCargo(0);
        }
    }
    public void fleeBandit(Bandit bandit, Region from) {
        this.player.setCurrReg(from);
        if (random.nextInt(101) > 90 - 5 * this.player.getpPoint()) {
            bandit.setSpeak("Successfully escaped.");
        } else {
            this.player.setCredit(0);
            this.player.getShip().changeHealth(-20);
            bandit.setSpeak("Unable to escape. Bandit took all the money and damaged the ship.");
        }
    }

    public void fightBandit(Bandit bandit) {
        if (random.nextInt(101) > 90 - 5 * this.player.getfPoint()) {
            int loot = random.nextInt(3000);
            this.player.changeCredit(loot);
            bandit.setSpeak("Fought off the bandit. Bandit dropped " + loot + " as they ran away.");
        } else {
            this.player.setCredit(0);
            this.player.getShip().changeHealth(-60);
            bandit.setSpeak("Failed to fight off the bandit. "
                + "They took all the money and damaged the ship");
        }
    }

    public boolean checkKarmaTrigger() {
        return random.nextInt(21) < karma;
    }

    public void karmaConsequence() {
        this.player.getShip().setHealth(1);
        for (Item i: player.getShip().getInventory()) {
            i.setAmount(0);
        }
        this.player.getShip().setCargo(0);
        karma = 0;
    }

    public void refuel(int refuelAmount) {
        this.player.changeCredit(-refuelAmount);
        this.player.getShip().changeFuel(refuelAmount);
    }

}
