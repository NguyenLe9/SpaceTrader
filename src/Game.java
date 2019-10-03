public class Game {
    Player player;
    private String difficulty;
    public enum Region {
        ASGARD, EARTH, JOTUNHEIM, VANAHEIM,
	    SOVEREIGNWORLD, EGO, MORAG, TITAN, XANDAR, GARDEN
	    }
    public enum TechLevel { PREAG, AGRICULTURE, MEDIEVAL,
	    RENAISSANCE, INDUSTRIAL, MODERN, FUTURISTIC}
    public Game(int pPoint, int mPoint, int ePoint,
		int fPoint, int credit, String difficulty) {
        this.setUpPlayer(pPoint, mPoint, ePoint, fPoint, credit);
        this.setUpDifficulty(difficulty);
        Universe universe = new Universe();
    }
    public void setUpPlayer
	(int pPoint, int mPoint, int ePoint, int fPoint, int credit) {
        player.setpPoint(pPoint);
        player.setmPoint(mPoint);
        player.setePoint(ePoint);
        player.setfPoint(fPoint);
        player.setCredit(credit);
        player.setCurrentRegion(this.getRandomRegionName());
    }
    public Region getRandomRegionName() {
        Region name = Region.values()
	    [(int)(Math.random()*Region.values().length)];
        return name;
    }
    public void setUpDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

}
