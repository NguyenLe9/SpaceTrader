import java.util.Random;

public class Item {
    private String name;
    private int price;
    private int buyPrice;
    private int sellPrice;
    private int amount;
    private Random random = new Random();

    public Item(String name, int price, int amount) {
        this.name = name;
        this.price = generatePrice(price);
        this.buyPrice = price;
        // place holder, make formula for sell price
        this.sellPrice = price;
        this.amount = generateAmount(amount);
    }

    public String getName() {
        return this.name;
    }
    public int getPrice() {
        return this.price;
    }
    public int getBuyPrice() {
        return this.buyPrice;
    }
    public int getSellPrice() {
        return this.sellPrice;
    }
    public int getAmount() {
        return this.amount;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void changeAmount(int amount) {
        this.amount += amount;
    }
    public int generatePrice(int price) {
        return random.nextInt(price * 2);
    }
    public int generateAmount(int amount) {
        if (amount == 0) {
            return 0;
        }
        return random.nextInt(amount * 2);
    }
}