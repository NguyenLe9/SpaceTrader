public class Bandit implements NonPlayable {
    private int loot;
    // this equals to bandit's credit?

    public Bandit() {
        this.loot = 0;
    }

    // Use this constructor to create a Bandit
    // with a number of credit demand (= loot)
    public Bandit(int loot) {
        this.loot = loot;
    }

    public int getLoot() {
        return this.loot;
    }

    // take from player
    public void gainLoot(Player player) {
        if (player.getCredit() < loot) {
            player.changeCredit(0);
        } else {
            player.changeCredit(player.getCredit() - this.loot);
        }
    }

    public String getSpeak() {
        return "Give me " + loot + " now!!!";
    }

    public String getImageName() {
        return "bandit.jpg";
    }
}