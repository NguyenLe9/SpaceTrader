public abstract class Ship {

    private int cargo;
    private int maxCargo;
    private int fuel;
    private int maxFuel;
    private int health;
    private int maxHealth;
    private Item[] inventory = new Item[] {new Item("Food", 0, 0),
                                     new Item("Wood", 0, 0),
                                     new Item("Iron", 0, 0),
                                     new Item("Gold", 0, 0),
                                     new Item("Balloons", 0, 0),
                                     new Item("Fancy Painting", 0, 0),
                                     new Item("Generators", 0, 0),
                                     new Item("Medicine", 0, 0),
                                     new Item("Computer", 0, 0),
                                     new Item("Super Computer", 0, 0),
                                     new Item("HAL 1999", 0, 0),
                                     new Item("Lightsabers", 0, 0),
                                     new Item("Unobtainium", 0, 0)};;

    protected void setMax() {
        maxCargo = this.cargo;
        maxFuel = this.fuel;
        maxHealth = this.health;
    }


    public void changeCargo(int i) {
        this.cargo += i;
    }

    public void changeFuel(int i) {
        this.fuel += i;
    }

    public void changeHealth(int i) {
        this.health += i;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCargo() {
        return this.cargo;
    }

    public int getFuel() {
        return this.fuel;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxCargo() {
        return this.maxCargo;
    }

    public int getMaxFuel() {
        return this.maxFuel;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public Item[] getInventory() {
        return this.inventory;
    }

}