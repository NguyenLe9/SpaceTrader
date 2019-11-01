public class Police implements NonPlayable {
    private Item[] suspected; // suspected item
    private int damage;
    private String speak;


    public Police(Item[] inventory) {
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
        String items = "";
        if (suspected.length == 1) {
            items = suspected[0].getName();
        } else {
            for (Item item : suspected) {
                items += item.getName() + ", ";
            }
            items = items.substring(0, items.length() - 2);
        }
        this.speak = "\"I'm going to consficate " + items + ".\"";
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

    public void setSpeak(String s) {
        this.speak = s;
    }

    public String getSpeak() {
        return this.speak;
    }

    public String getImageName() {
        return "Graphics/police.jpg";
    }
}