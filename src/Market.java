
public class Market {

    private Item[] item;
    // array for base values of amounts available for each item in each tech level
    private int[][] avail = new int[][] {{60, 40, 80, 30, 50, 20, 30, 100, 100, 20, 5, 10, 1},
                                         {90, 60, 100, 60, 1000, 30, 30, 30, 20, 3, 0, 1, 0},
                                         {80, 60, 150, 100, 3, 10, 20, 20, 1, 0, 0, 0, 0},
                                         {120, 80, 100, 70, 0, 20, 0, 25, 0, 0, 0, 0, 0},
                                         {150, 100, 100, 50, 0, 3, 0, 5, 0, 0, 0, 0, 0},
                                         {150, 130, 20, 13, 0, 0, 0, 3, 0, 0, 0, 0, 0},
                                         {50, 150, 50, 50, 0, 0, 0, 3, 0, 0, 0, 0, 0}};

    public Market(int tech, boolean winningItem, String name) {
        if (!winningItem) {
            this.item = new Item[]{new Item("Food", 1 * tech, avail[tech - 1][0]),
                                   new Item("Wood", 3 * tech, avail[tech - 1][1]),
                                   new Item("Iron", 15 * tech, avail[tech - 1][2]),
                                   new Item("Gold", 50 * tech, avail[tech - 1][3]),
                                   new Item("Balloons", 5 * tech, avail[tech - 1][4]),
                                   new Item("Fancy Painting", 150 * tech, avail[tech - 1][5]),
                                   new Item("Generators", 30 * tech, avail[tech - 1][6]),
                                   new Item("Medicine", 50 * tech, avail[tech - 1][7]),
                                   new Item("Computer", 100 * tech, avail[tech - 1][8]),
                                   new Item("Super Computer", 500 * tech, avail[tech - 1][9]),
                                   new Item("HAL 1999", 1999 * tech, avail[tech - 1][10]),
                                   new Item("Lightsabers", 9999 * tech, avail[tech - 1][11]),
                                   new Item("Unobtainium", 1000000 * tech, avail[tech - 1][12])};
        } else {
            this.item = new Item[]{new Item("Food", 1 * tech, avail[tech - 1][0]),
                                   new Item("Wood", 3 * tech, avail[tech - 1][1]),
                                   new Item("Iron", 15 * tech, avail[tech - 1][2]),
                                   new Item("Gold", 50 * tech, avail[tech - 1][3]),
                                   new Item("Balloons", 5 * tech, avail[tech - 1][4]),
                                   new Item("Fancy Painting", 150 * tech, avail[tech - 1][5]),
                                   new Item("Generators", 30 * tech, avail[tech - 1][6]),
                                   new Item("Medicine", 50 * tech, avail[tech - 1][7]),
                                   new Item("Computer", 100 * tech, avail[tech - 1][8]),
                                   new Item("Super Computer", 500 * tech, avail[tech - 1][9]),
                                   new Item("HAL 1999", 1999 * tech, avail[tech - 1][10]),
                                   new Item("Lightsabers", 9999 * tech, avail[tech - 1][11]),
                                   new Item(name + "'s Universe", 1000002 * tech, 1)};
        }
    }

    public Item[] getItem() {
        return this.item;
    }

}