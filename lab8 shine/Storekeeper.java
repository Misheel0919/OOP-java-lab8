import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storekeeper {
    private static int incomeCounter = 1;
    private static int expenseCounter = 1;

    private String storekeeperId;
    private String name;
    private Warehouse warehouse;

    public Storekeeper(String storekeeperId, String name, Warehouse warehouse) {
        if (storekeeperId == null || storekeeperId.isBlank()) {
            throw new IllegalArgumentException("Storekeeper id cannot be empty.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Storekeeper name cannot be empty.");
        }
        if (warehouse == null) {
            throw new IllegalArgumentException("Warehouse cannot be null.");
        }
        this.storekeeperId = storekeeperId;
        this.name = name;
        this.warehouse = warehouse;
        this.warehouse.setStorekeeper(this);
    }

    public Income receiveGoods(String incomeNo, LocalDate date, List<Receipt> items, String supplier) {
        String generatedNo = incomeNo == null || incomeNo.isBlank()
                ? String.format("IN-%04d", incomeCounter++)
                : incomeNo;
        List<ReceiptItem> converted = convert(items);
        Income income = new Income(generatedNo, date, supplier, converted);
        warehouse.registerIncome(income);
        return income;
    }

    public Expense issueGoods(String expenseNo, LocalDate date, List<Receipt> items, String receiver) {
        String generatedNo = expenseNo == null || expenseNo.isBlank()
                ? String.format("EX-%04d", expenseCounter++)
                : expenseNo;
        List<ReceiptItem> converted = convert(items);
        Expense expense = new Expense(generatedNo, date, receiver, converted);
        warehouse.registerExpense(expense);
        return expense;
    }

    public InventoryReport viewInventoryReport(List<Product> selectedProducts) {
        InventoryReport report = new InventoryReport(warehouse, LocalDate.now(), warehouse.currentStockMap());
        if (selectedProducts == null || selectedProducts.isEmpty()) {
            report.generateAllProductsReport();
        } else {
            report.generateSelectedProductsReport(selectedProducts);
        }
        return report;
    }

    public StorekeeperReport viewStorekeeperReport(LocalDate ehlehOgnoo, LocalDate duusahOgnoo, List<Product> selectedProducts) {
        StorekeeperReport report = new StorekeeperReport(warehouse, ehlehOgnoo, duusahOgnoo, new ArrayList<>());
        if (selectedProducts == null || selectedProducts.isEmpty()) {
            report.generateAllProductsReport();
        } else {
            report.generateSelectedProductsReport(selectedProducts);
        }
        return report;
    }

    public StockCount counting(Product product, LocalDate countingDate, int actualQuantity) {
        StockCount stockCount = new StockCount(product, countingDate, product.getQuantity(), actualQuantity);
        warehouse.registerStockCount(stockCount);
        return stockCount;
    }

    private List<ReceiptItem> convert(List<Receipt> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be empty.");
        }
        List<ReceiptItem> converted = new ArrayList<>();
        for (Receipt receipt : items) {
            converted.add(new ReceiptItem(receipt.getProduct(), receipt.getQuantity()));
        }
        return converted;
    }

    public String getStorekeeperId() {
        return storekeeperId;
    }

    public String getName() {
        return name;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}
