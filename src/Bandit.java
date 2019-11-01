public class Bandit implements NonPlayable {
    private int loot;
    // this equals to bandit's credit?
    private String speak;

    public Bandit(int loot) {
        this.loot = loot;
        this.speak = "\"Give me " + loot + " now!!!\"";
    }

    public int getLoot() {
        return this.loot;
    }

    public void setSpeak(String s) {
        this.speak = s;
    }

    public String getSpeak() {
        return this.speak;
    }

    public String getImageName() {
        return "Graphics/bandit.jpg";
    }
}