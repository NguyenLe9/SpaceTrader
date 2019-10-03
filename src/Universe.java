import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;

public class Universe {
    private final String name = "SpaceTrader";
    private static List<Region> regions;

    public static Set<Integer> xCoords = new HashSet<>(400);
    public static Set<Integer> yCoords = new HashSet<>(400);

    private static void coordinatesHelper() {
        for (int i = -200; i <= 200; i++) {
            xCoords.add(i);
            yCoords.add(i);
        }
    }

    private static Universe newUniverse = null;

    private Universe() {
        coordinatesHelper();
        regions = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Region newRegion = new Region();
            regions.add(newRegion);
        }
    }

    public static Universe getUniverse() {
        if (newUniverse == null) {
            newUniverse = new Universe();
        }
        return newUniverse;
    }

    public String toString() {
        String regs = "";
        for (Region region : regions) {
            regs += region.getName() + " ";
        }
        return "Universe name: " + name + "\nRegions: " + regs;
    }

    public static void main(String[] args) {
        Universe newUniverse = Universe.getUniverse();
        System.out.println(newUniverse);
    }
}
