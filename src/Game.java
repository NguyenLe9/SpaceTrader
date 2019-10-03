public class Game {
    private Universe universe;
    private Player player;
    private String difficulty;

    // public enum Region {
    //     ASGARD, EARTH, JOTUNHEIM, VANAHEIM,
	   //  SOVEREIGNWORLD, EGO, MORAG, TITAN, XANDAR, GARDEN
    // }
    public Game(int pPoint, int mPoint, int ePoint,int fPoint, int credit,
            String difficulty) {
        this.universe = Universe.getUniverse();
        this.player = new Player(pPoint, fPoint, mPoint, ePoint, credit,
            universe.pickRandomRegion());
        this.setDifficulty(difficulty);
    }
    // public Region getRandomRegionName() {
    //     Region name = Region.values()[(int)(Math.random()*
    //         Region.values().length)];
    //     return name;
    // }
    public double getDistance(Region destination) {
        return java.lang.Math.sqrt(Math.pow(this.player.getCurrentRegion().getX() - destination.getX(),2) +
                                   Math.pow(this.player.getCurrentRegion().getY() - destination.getY(), 2));
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

}
