import java.util.Random;

public class Item {
    private String name;
    private int price;
    private int priceForCalc;
    private int amount;
    private Random random = new Random();

    public Item(String name, int price, int amount) {
        this.name = name;
        this.price = generatePrice(price);
        this.priceForCalc = price;
        this.amount = generateAmount(amount);
    }

    public String getName() {
        return this.name;
    }
    public int getPrice() {
        return this.price;
    }
    public int getAmount() {
        return this.amount;
    }
    public int getPriceForCalc() {
        return this.priceForCalc;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void changeAmount(int amount) {
        this.amount += amount;
    }
    public int generatePrice(int price) {
        if (price == 0) {
            return 0;
        }
        return random.nextInt(price * 2);
    }
    public int generateAmount(int amount) {
        if (amount == 0) {
            return 0;
        }
        return random.nextInt(amount * 2);
    }
}