import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillCalculator {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private List<String> items;
    private List<Double> prices;
    private int numPeople;

    public BillCalculator() {
        items = new ArrayList<>();
        prices = new ArrayList<>();
        numPeople = 0;
    }

    public void start() {
        enterNumPeople();
        addItems();
        displayResults();
    }

    private void enterNumPeople() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество людей:");
        while (true) {
            try {
                numPeople = Integer.parseInt(scanner.nextLine());
                if (numPeople == 1) {
                    System.out.println("Нет смысла считать и делить на 1 человека. Введите корректное количество гостей.");
                } else if (numPeople < 1) {
                    System.out.println("Некорректное значение. Введите корректное количество гостей.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректное значение. Введите корректное количество гостей.");
            }
        }
    }

    private void addItems() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите название товара или 'Завершить', чтобы завершить добавление товаров:");
            String item = scanner.nextLine();
            if (item.equalsIgnoreCase("завершить")) {
                break;
            }
            double price;
            while (true) {
                try {
                    System.out.println("Введите стоимость товара (в формате рубли.копейки):");
                    price = Double.parseDouble(scanner.nextLine());
                    if (price <= 0) {
                        System.out.println("Некорректная стоимость товара. Введите положительное значение.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Некорректная стоимость товара. Введите корректное значение.");
                }
            }
            items.add(item);
            prices.add(price);
            System.out.println("Товар успешно добавлен.");
        }
    }

    private void displayResults() {
        System.out.println("\nДобавленные товары:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i) + " - " + decimalFormat.format(prices.get(i)) + " рублей");
        }
        double totalAmount = 0;
        for (double price : prices) {
            totalAmount += price;
        }
        double perPersonAmount = totalAmount / numPeople;
        System.out.println("\nСумма, которую должен заплатить каждый человек:");
        System.out.println(decimalFormat.format(perPersonAmount) + " рубля");
    }

    public static void main(String[] args) {
        BillCalculator calculator = new BillCalculator();
        calculator.start();
    }
}
