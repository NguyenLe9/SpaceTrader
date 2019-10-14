public class Game {
    private Universe universe;
    private Player player;
    private String difficulty;

    public Game(int[] skills, int credit, String difficulty) {
        this.universe = Universe.getUniverse();
        this.player = new Player(skills[0], skills[1], skills[2], skills[3],
            credit, universe.pickRandomRegion());
        // this.player = new Player(skills, credit, "", universe.pickRandomRegion());
        this.setDifficulty(difficulty);
    }
    public double getDistance(Region destination) {
        return java.lang.Math.sqrt(Math.pow(this.player.getCurrReg().getX()
            - destination.getX(), 2) + Math.pow(this.player.getCurrReg().getY()
            - destination.getY(), 2));
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
        for (int i = 0; i < itemList.length; i++) {
            itemList[i].setBuyPrice((int) (itemList[i].getPrice()
                * java.lang.Math.pow(1.01, 16 - this.player.getmPoint())));
            itemList[i].setSellPrice((int) (itemList[i].getPrice()
                * java.lang.Math.pow(0.99, 16 - this.player.getmPoint())));
        }
    }

}
