package Order;
import java.util.ArrayList;
import java.util.List;
import Food.*;

public class Order implements Payable {
    private List<Product> products;

    public Order() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    @Override
    public void pay() {
        System.out.println("Оплата заказа выполнена.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Заказ:\n");
        if (products.isEmpty()) {
            sb.append("Заказ пуст.\n");
        } else {
            for (int i = 0; i < products.size(); i++) {
                sb.append((i + 1)).append(". ").append(products.get(i)).append("\n");
            }
            sb.append("Общая стоимость заказа: $").append(getTotalPrice());
        }
        return sb.toString();
    }
}