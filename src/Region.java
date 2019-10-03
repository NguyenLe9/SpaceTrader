import java.util.Set;

public class Region {
    private enum TechLevel {
        PREAG,
        AGRICULTURE,
        MEDIEVAL,
        RENAISSANCE,
        INDUSTRIAL,
        MODERN,
        FUTURISTIC;
    }
    public enum Name {
        ASGARD,
        EARTH,
        JOTUNHEIM,
        VANAHEIM,
        SOVEREIGNWORLD,
        EGO,
        MORAG,
        TITAN,
        XANDAR,
        GARDEN;
    }

    private int x;
    private int y;
    private TechLevel techLevel;
    private String name;
    private int index;


    public Region() {
        this.x = generateCoords(Universe.xCoords);
        this.y = generateCoords(Universe.yCoords);
        this.techLevel = generateTechLevel();
        this.name = generateName();
    }

    private TechLevel generateTechLevel() {
        int ordinal = (int) (Math.random() * 6);
        return TechLevel.values()[ordinal];
    }

    private int generateCoords(Set<Integer> coords) {
        int temp = (int) (-200 + (Math.random() * 400));
        if (coords.contains(temp)) {
            for (int i = 0; i <= 5; i++) {
                coords.remove(temp + i);
                coords.remove(temp - i);
            }
            return temp;
        } else { // if set doesn't contain --> invalid
            generateCoords(coords);
        }
        return temp;
    }

    private String generateName() {
        int rand = (int) (Math.random() * 99);
        int h = this.hashCode() % 100;
        return this.techLevel.name().substring(0,3) + h + rand;
    }

    // public String toString() {
    //     return "Name: " + this.name + "\nTech Level: " + this.techLevel
    //             + "\nCoordinates: (" + this.x + ", " + this.y + ")\n";
    // }

    // public static Name generateName() {
    //     Name name = Name.values()[(int)(Math.random()*
    //         Name.values().length)];
    //     return name;
    // }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public TechLevel getTechLevel() {
        return this.techLevel;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
