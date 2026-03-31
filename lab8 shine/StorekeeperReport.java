import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StorekeeperReport {
    private Warehouse warehouse;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<StorekeeperReportRow> rows;

    public StorekeeperReport(Warehouse warehouse, LocalDate startDate, LocalDate endDate,
                             List<StorekeeperReportRow> rows) {
        this.warehouse = warehouse;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rows = new ArrayList<>(rows == null ? List.of() : rows);
    }

    public void generateAllProductsReport() {
        rows = warehouse.buildReportRows(startDate, endDate, warehouse.getProducts());
        printReport();
    }

    public void generateSelectedProductsReport(List<Product> selectedProducts) {
        rows = warehouse.buildReportRows(startDate, endDate, selectedProducts);
        printReport();
    }

    public int calculateOpeningBalance(Product product) {
        return warehouse.calculateBalanceAt(product, startDate.minusDays(1));
    }

    public int calculateTotalReceipt(Product product) {
        return warehouse.calculateIncomeInRange(product, startDate, endDate);
    }

    public int calculateTotalIssue(Product product) {
        return warehouse.calculateExpenseInRange(product, startDate, endDate);
    }

    public int calculateClosingBalance(Product product) {
        return warehouse.calculateBalanceAt(product, endDate);
    }

    public void printReport() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("=".repeat(90));
        System.out.println("                           STOREKEEPER REPORT");
        System.out.println("=".repeat(90));
        System.out.printf("Warehouse : %s%n", warehouse.getWarehouseName());
        System.out.printf("Period    : %s to %s%n", startDate.format(fmt), endDate.format(fmt));
        System.out.println("-".repeat(90));
        System.out.printf("%-18s | %-8s | %-8s | %-8s | %-8s | %-6s%n",
                "Product", "Opening", "Receipt", "Expense", "Closing", "Unit");
        System.out.println("-".repeat(90));
        for (StorekeeperReportRow row : rows) {
            System.out.printf("%-18s | %-8d | %-8d | %-8d | %-8d | %-6s%n",
                    row.getProduct().getProductName(),
                    row.getOpeningBalance(),
                    row.getTotalReceipt(),
                    row.getTotalExpense(),
                    row.getClosingBalance(),
                    row.getProduct().getUnitOfMeasure());
        }
        System.out.println("=".repeat(90));
        System.out.println("Note: closing balance also reflects stock count adjustments within the period.");
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<StorekeeperReportRow> getRows() {
        return List.copyOf(rows);
    }
}
