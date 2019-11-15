import java.util.Set;
import java.awt.*;
import java.io.*;

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
    private Market market;
    private Image image;
    private static int regNum = 0;

    public Region(boolean special, String playerName) {
        try {
            this.x = generateCoords(Universe.getXCoord());
            this.y = generateCoords(Universe.getYCoord());
            this.techLevel = generateTechLevel();
            this.name = generateName();
            this.index = regNum;
            if (!special) {
                this.market = new Market(techToInt(this.techLevel),special,null);
            } else {
                this.market = new Market(techToInt(this.techLevel),special,playerName);
            }
            this.image = javax.imageio.ImageIO.read(
						    new File("Graphics/reg" + regNum + ".jpg"));
            regNum++;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private TechLevel generateTechLevel() {
        int ordinal = (int) (Math.random() * 7);
        return TechLevel.values()[ordinal];
    }

    private int generateCoords(Set<Integer> coords) {
        // int temp = (int) ((Math.random() * 400));
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
        return this.techLevel.name().substring(0, 3) + h + rand;
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

    public String getCoord() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public int getIndex() {
        return this.index;
    }

    public Image getImage() {
        return this.image;
    }

    public Market getMarket() {
        return this.market;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int techToInt(TechLevel tech) {
        if (tech == TechLevel.PREAG) {
            return 7;
        } else if (tech == TechLevel.AGRICULTURE) {
            return 6;
        } else if (tech == TechLevel.MEDIEVAL) {
            return 5;
        } else if (tech == TechLevel.RENAISSANCE) {
            return 4;
        } else if (tech == TechLevel.INDUSTRIAL) {
            return 3;
        } else if (tech == TechLevel.MODERN) {
            return 2;
        } else {
            return 1;
        }
    }
}
