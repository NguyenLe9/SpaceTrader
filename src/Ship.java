public class Ship {
    private enum Type {
        STARSHIP,
        ROCKET,
        WARRIOR,
        ORBITER,
        MARTIAN,
        JET;
    }

    private Type type;
    private Type finalType;
    private int cargo;
    private int maxCargo;
    private int fuel;
    private int maxFuel;
    private int health;
    private int maxHealth;
    private Item[] inventory;

    private void setShip(Type type, int cargo, int fuel, int health) {
        this.type = type;
        this.cargo = cargo;
        this.fuel = fuel;
        this.health = health;
    }

    private void setMax() {
        finalType = this.type;
        maxCargo = this.cargo;
        maxFuel = this.fuel;
        maxHealth = this.health;
    }

    public Ship(String type) {
        if (type.toLowerCase().equals(Type.STARSHIP.name().toLowerCase())) {
            setShip(Type.STARSHIP, 200, 100, 150);
        } else if (type.toLowerCase().equals(Type.ROCKET.name().toLowerCase())) {
            setShip(Type.ROCKET, 50, 200, 100);
        } else if (type.toLowerCase().equals(Type.WARRIOR.name().toLowerCase())) {
            setShip(Type.WARRIOR, 50, 200, 200);
        } else if (type.toLowerCase().equals(Type.ORBITER.name().toLowerCase())) {
            setShip(Type.ORBITER, 100, 100, 100);
        } else if (type.toLowerCase().equals(Type.MARTIAN.name().toLowerCase())) {
            setShip(Type.MARTIAN, 150, 200, 100);
        } else if (type.toLowerCase().equals(Type.JET.name().toLowerCase())) {
            setShip(Type.JET, 100, 200, 150);
        } else {
            System.out.println("Invalid ship type");
        }
        setMax();
        setCargo(0);
        this.inventory = new Item[] {new Item("Food", 0, 0),
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
                                     new Item("Unobtainium", 0, 0)};
    }

    public void changeCargo(int i) {
        this.cargo += i;
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

    public Type getType() {
        return this.type;
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