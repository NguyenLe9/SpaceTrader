public class Police implements NonPlayable {
    private Item[] suspected; // suspected item
    private int damage;
    private String speak;


    public Police(Item[] inventory) {
        generateSuspected(inventory);
        String items = "";
        for (Item item: suspected) {
            items += item.getName() + ", ";
        }
        if (items.length() > 2) {
            items = items.substring(0, items.length() - 2);
        }
        this.speak = "\"I am going to confiscate " + items + ".\"";
    }

    public Item[] getSuspected() {
        return suspected;
    }

    public int getDamage() {
        return this.damage;
    }

    public void generateSuspected(Item[] items) {
        int cargo = 0;
        for (Item i: items) {
            cargo += i.getAmount();
        }
        suspected = new Item[(int) (Math.random() * (cargo / 2)) + 1];
        for (int i = 0; i < suspected.length; i++) {
            int itemIndex = (int) (Math.random() * items.length);
            while (items[itemIndex].getAmount() == 0) {
                itemIndex = (int) (Math.random() * items.length);
            }
            suspected[i] = items[itemIndex];
        }
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