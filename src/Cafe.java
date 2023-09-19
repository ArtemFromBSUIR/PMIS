import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import Food.*;
import Order.*;

public class Cafe {
    private static List<Product> menu;
    private static Order order;
    private static Scanner scanner;

    public static void main(String[] args) {
        menu = createMenu();
        order = new Order();
        scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = getIntegerInput("Выберите действие: ");

            switch (choice) {
                case 1:
                    displayMenuItems();
                    break;
                case 2:
                    addToOrder();
                    break;
                case 3:
                    removeFromOrder();
                    break;
                case 4:
                    displayOrder();
                    break;
                case 5:
                    payOrder();
                    exit = true;
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private static List<Product> createMenu() {
        List<Product> menu = new ArrayList<>();
        menu.add(new Dish("Паста", 10.99, "Спагетти с соусом Болоньезе"));
        menu.add(new Dish("Бургер", 8.99, "Говяжий бургер с картофельными дольками"));
        menu.add(new Drink("Кола", 2.99, "0.5 литра"));
        menu.add(new Drink("Чай", 1.99, "Черный чай"));
        menu.add(new Dish("Салат Цезарь", 7.99, "Зеленый салат с куриной грудкой и соусом Цезарь"));
        menu.add(new Dish("Пицца Маргарита", 18.99, "Традиционная итальянская пицца с томатами и моцареллой"));
        menu.add(new Drink("Капучино", 3.99, "Классический кофе с молоком и пенкой"));
        menu.add(new Drink("Сок апельсиновый", 2.49, "Свежевыжатый апельсиновый сок"));
        return menu;
    }

    private static void displayMenu() {
        //System.out.println("Меню:");
        System.out.println("1. Просмотреть меню");
        System.out.println("2. Добавить продукт в заказ");
        System.out.println("3. Удалить продукт из заказа");
        System.out.println("4. Просмотреть заказ");
        System.out.println("5. Оплатить заказ и выйти");
    }

    private static void displayMenuItems() {
        System.out.println("Меню:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }

    private static void addToOrder() {
        displayMenuItems();
        int choice = getIntegerInput("Выберите продукт для добавления в заказ: ");
        if (choice >= 1 && choice <= menu.size()) {
            Product selectedProduct = menu.get(choice - 1);
            order.addProduct(selectedProduct);
            System.out.println(selectedProduct.getName() + " добавлен в заказ.");
        } else {
            System.out.println("Некорректный выбор продукта.");
        }
    }

    private static void removeFromOrder() {
        List<Product> products = order.getProducts();
        if (products.isEmpty()) {
            System.out.println("Заказ пуст.");
            return;
        }
        System.out.println("Ваш заказ:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
        int choice = getIntegerInput("Выберите продукт для удаления из заказа: ");
        if (choice >= 1 && choice <= products.size()) {
            Product selectedProduct = products.get(choice - 1);
            order.removeProduct(selectedProduct);
            System.out.println(selectedProduct.getName() + " удален из заказа.");
        } else {
            System.out.println("Некорректный выбор продукта.");
        }
    }

    private static void displayOrder() {
        System.out.println(order);
    }

    private static void payOrder() {
        double totalPrice = order.getTotalPrice();
        System.out.println("Общая стоимость заказа: $" + totalPrice);
        order.pay();
    }

    private static int getIntegerInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Некорректный ввод. Попробуйте снова: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}