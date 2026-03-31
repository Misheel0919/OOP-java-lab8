import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse("WH-01", "Central Warehouse");
        Storekeeper storekeeper = new Storekeeper("SK-001", "G.Enkhjin", warehouse);

        Product chips = new Product("FOOD-001", "Chips", "pcs", 100);
        Product sugar = new Product("FOOD-002", "Sugar", "kg", 50);
        Product yogurt = new Product("FOOD-003", "Yogurt", "pcs", 200);
        Product noodle = new Product("FOOD-004", "Instant Noodle", "pcs", 150);

        warehouse.addProduct(chips);
        warehouse.addProduct(sugar);
        warehouse.addProduct(yogurt);
        warehouse.addProduct(noodle);

        System.out.println("\n=== INITIAL INVENTORY ===");
        storekeeper.viewInventoryReport(null);

        Income income1 = storekeeper.receiveGoods(
                null,
                LocalDate.of(2026, 3, 25),
                List.of(new Receipt(chips, 20), new Receipt(sugar, 15), new Receipt(noodle, 30)),
                "Altan Taria LLC"
        );
        income1.printReceipt();

        Expense expense1 = storekeeper.issueGoods(
                null,
                LocalDate.of(2026, 3, 27),
                List.of(new Receipt(chips, 10), new Receipt(yogurt, 25)),
                "Branch Store #2"
        );
        expense1.printReceipt();

        StockCount count1 = storekeeper.counting(chips, LocalDate.of(2026, 3, 29), 107);
        count1.printReport();

        System.out.println("\n=== CURRENT INVENTORY ===");
        storekeeper.viewInventoryReport(null);

        System.out.println("\n=== SELECTED PRODUCTS INVENTORY ===");
        storekeeper.viewInventoryReport(List.of(chips, sugar));

        System.out.println("\n=== STOREKEEPER PERIOD REPORT ===");
        storekeeper.viewStorekeeperReport(
                LocalDate.of(2026, 3, 24),
                LocalDate.of(2026, 3, 31),
                null
        );
    }
}
