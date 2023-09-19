package Food;

 public class Drink extends Product {
    private String size;

    public Drink(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }


    @Override
    public String toString() {
        return super.toString() + " - " + size;
    }
}
