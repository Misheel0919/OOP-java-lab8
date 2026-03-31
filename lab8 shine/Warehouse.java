import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Warehouse {
    private String warehouseCode;
    private String warehouseName;
    private Storekeeper storekeeper;
    private InventoryReport inventoryReport;
    private List<Income> incomes;
    private List<Expense> expenses;
    private List<StockCount> stockCounts;
    private Map<Product, Integer> initialStocks;

    public Warehouse(String warehouseCode, String warehouseName) {
        if (warehouseCode == null || warehouseCode.isBlank()) {
            throw new IllegalArgumentException("Warehouse code cannot be empty.");
        }
        if (warehouseName == null || warehouseName.isBlank()) {
            throw new IllegalArgumentException("Warehouse name cannot be empty.");
        }
        this.warehouseCode = warehouseCode;
        this.warehouseName = warehouseName;
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.stockCounts = new ArrayList<>();
        this.initialStocks = new LinkedHashMap<>();
        this.inventoryReport = new InventoryReport(this, LocalDate.now(), initialStocks);
    }

    public void setStorekeeper(Storekeeper storekeeper) {
        this.storekeeper = storekeeper;
    }

    public void addProduct(Product product) {
        initialStocks.putIfAbsent(product, product.getQuantity());
        inventoryReport.storeProductStock(product, product.getQuantity());
    }

    public void registerIncome(Income income) {
        incomes.add(income);
        for (ReceiptItem item : income.getItems()) {
            item.applyIncome();
            inventoryReport.storeProductStock(item.getProduct(), item.getProduct().getQuantity());
            initialStocks.putIfAbsent(item.getProduct(), item.getProduct().getQuantity() - item.getQuantity());
        }
        inventoryReport = new InventoryReport(this, income.getDate(), currentStockMap());
    }

    public void registerExpense(Expense expense) {
        expenses.add(expense);
        for (ReceiptItem item : expense.getItems()) {
            item.applyExpense();
            inventoryReport.storeProductStock(item.getProduct(), item.getProduct().getQuantity());
            initialStocks.putIfAbsent(item.getProduct(), item.getProduct().getQuantity() + item.getQuantity());
        }
        inventoryReport = new InventoryReport(this, expense.getDate(), currentStockMap());
    }

    public void registerStockCount(StockCount stockCount) {
        stockCounts.add(stockCount);
        stockCount.updateBalance(inventoryReport);
        inventoryReport = new InventoryReport(this, stockCount.getCountingDate(), currentStockMap());
        initialStocks.putIfAbsent(stockCount.getProduct(), stockCount.getActualQuantity());
    }

    public void updateProductStock(Product product, int quantity) {
        inventoryReport.storeProductStock(product, quantity);
    }

    public int findProductStock(Product product) {
        return inventoryReport.checkCurrentBalance(product);
    }

    public Map<Product, Integer> currentStockMap() {
        Map<Product, Integer> current = new LinkedHashMap<>();
        for (Product product : initialStocks.keySet()) {
            current.put(product, product.getQuantity());
        }
        return current;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(initialStocks.keySet());
    }

    public int getInitialQuantity(Product product) {
        return initialStocks.getOrDefault(product, product.getQuantity());
    }

    public int calculateBalanceAt(Product product, LocalDate date) {
        int balance = getInitialQuantity(product);
        List<Event> events = getEventsForProduct(product);
        for (Event event : events) {
            if (event.date().isAfter(date)) {
                break;
            }
            switch (event.type()) {
                case INCOME -> balance += event.quantity();
                case EXPENSE -> balance -= event.quantity();
                case COUNT -> balance = event.quantity();
            }
        }
        return balance;
    }

    public int calculateIncomeInRange(Product product, LocalDate startDate, LocalDate endDate) {
        int total = 0;
        for (Income income : incomes) {
            if (!income.getDate().isBefore(startDate) && !income.getDate().isAfter(endDate)) {
                for (ReceiptItem item : income.getItems()) {
                    if (item.getProduct().equals(product)) {
                        total += item.getQuantity();
                    }
                }
            }
        }
        return total;
    }

    public int calculateExpenseInRange(Product product, LocalDate startDate, LocalDate endDate) {
        int total = 0;
        for (Expense expense : expenses) {
            if (!expense.getDate().isBefore(startDate) && !expense.getDate().isAfter(endDate)) {
                for (ReceiptItem item : expense.getItems()) {
                    if (item.getProduct().equals(product)) {
                        total += item.getQuantity();
                    }
                }
            }
        }
        return total;
    }

    public List<StorekeeperReportRow> buildReportRows(LocalDate startDate, LocalDate endDate, List<Product> selectedProducts) {
        List<StorekeeperReportRow> rows = new ArrayList<>();
        for (Product product : selectedProducts) {
            int opening = calculateBalanceAt(product, startDate.minusDays(1));
            int receipt = calculateIncomeInRange(product, startDate, endDate);
            int expense = calculateExpenseInRange(product, startDate, endDate);
            int closing = calculateBalanceAt(product, endDate);
            rows.add(new StorekeeperReportRow(product, opening, receipt, expense, closing));
        }
        return rows;
    }

    private List<Event> getEventsForProduct(Product product) {
        List<Event> events = new ArrayList<>();
        for (Income income : incomes) {
            for (ReceiptItem item : income.getItems()) {
                if (item.getProduct().equals(product)) {
                    events.add(new Event(income.getDate(), EventType.INCOME, item.getQuantity()));
                }
            }
        }
        for (Expense expense : expenses) {
            for (ReceiptItem item : expense.getItems()) {
                if (item.getProduct().equals(product)) {
                    events.add(new Event(expense.getDate(), EventType.EXPENSE, item.getQuantity()));
                }
            }
        }
        for (StockCount count : stockCounts) {
            if (count.getProduct().equals(product)) {
                events.add(new Event(count.getCountingDate(), EventType.COUNT, count.getActualQuantity()));
            }
        }
        events.sort(Comparator.comparing(Event::date).thenComparing(e -> e.type().ordinal()));
        return events;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public Storekeeper getStorekeeper() {
        return storekeeper;
    }

    public InventoryReport getInventoryReport() {
        return inventoryReport;
    }

    public List<Income> getIncomes() {
        return List.copyOf(incomes);
    }

    public List<Expense> getExpenses() {
        return List.copyOf(expenses);
    }

    public List<StockCount> getStockCounts() {
        return List.copyOf(stockCounts);
    }

    private enum EventType { INCOME, EXPENSE, COUNT }
    private record Event(LocalDate date, EventType type, int quantity) {}
}
