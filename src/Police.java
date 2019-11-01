public class Police implements NonPlayable {
    // private Item[] inventory; // store list of items
    private Item[] suspected; // suspected item
    private int damage;

    public Police() {
        // inventory = null;
        suspected = null;
    }

    public Police(Item[] inventory) {
        // int length = player.getShip().getInventory().length;
        // inventory = new Item[length];
        // for (int i = 0; i < length; i++) {
        //     inventory[i] = player.getShip().getInventory()[i];
        // } // clone inventory
        int numItem = (int) (Math.random() * (inventory.length)); // random # of items
        suspected = new Item[numItem];
        for (int i = 0; i < numItem; i++) {
            int itemIndex = (int) (Math.random() * (inventory.length));
            if (inventory[itemIndex] != null) {
                suspected[i] = inventory[itemIndex];
            }
            // whether items are truly stolen is unknown
            inventory[itemIndex] = null;
        }
    }

    public Item[] getSuspected() {
        return suspected;
    }

    public int getDamage() {
        return this.damage;
    }

    public void attack(Player player) {
        this.damage = 18 - player.getfPoint(); // the higher the Fighter skill,
        // the lower the damage. change the math.
        int newHealth = player.getShip().getHealth() - damage;
        player.getShip().setHealth(newHealth);
    }

    public String getSpeak() {
        String items = "";
        if (suspected.length == 1) {
            items = suspected[0].getName();
        } else {
            for (Item item : suspected) {
                items += item.getName() + ", ";
            }
            items = items.substring(0, items.length() - 2);
        }
        return "I'm going to consficate " + items;
    }

    public String getImageName() {
        return "Graphics/police.jpg";
    }
}