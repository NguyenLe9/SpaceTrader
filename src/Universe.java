import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Random;

public class Universe {
    private final String name = "SpaceTrader";
    private static List<Region> regions;
    private final Random random = new Random();

    private static Set<Integer> xCoords = new HashSet<>(400);
    private static Set<Integer> yCoords = new HashSet<>(400);

    private static void coordinatesHelper() {
        // for (int i = 0; i <= 400; i++) {
        for (int i = -200; i <= 200; i++) {
            xCoords.add(i);
            yCoords.add(i);
        }
    }

    private static Universe newUniverse = null;

    private Universe(String playerName) {
        coordinatesHelper();
        regions = new ArrayList<>();
        int random = (int)(Math.random() * ((9 - 0) + 1)) + 0;
        for (int i = 0; i < 10; i++) {
            Region newRegion;
            if (i == random) {
                newRegion = new Region(true, playerName);
            } else {
                newRegion = new Region(false, null);
            }
            // newRegion.setIndex(i);
            regions.add(newRegion);
        }
    }

    public static Universe getUniverse(String playerName) {
        if (newUniverse == null) {
            newUniverse = new Universe(playerName);
        }
        return newUniverse;
    }
    public Region pickRandomRegion() {
        return regions.get(random.nextInt(regions.size() + 1));
    }

    public List<Region> getRegionList() {
        return this.regions;
    }
    public static Set<Integer> getXCoord() {
        return xCoords;
    }
    public static Set<Integer> getYCoord() {
        return yCoords;
    }
    // public String toString() {
    //     String regs = "";
    //     for (Region region : regions) {
    //         regs += region.getName() + " ";
    //     }
    //     return "Universe name: " + name + "\nRegions: " + regs;
    // }

    // public static void main(String[] args) {
    //     Universe newUniverse = Universe.getUniverse();
    //     System.out.println(newUniverse);
    // }
}
