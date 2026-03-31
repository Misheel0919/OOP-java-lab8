import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InventoryReport {
    private Warehouse warehouse;
    private LocalDate reportDate;
    private Map<Product, Integer> productStocks;

    public InventoryReport(Warehouse warehouse, LocalDate reportDate, Map<Product, Integer> productStocks) {
        this.warehouse = warehouse;
        this.reportDate = reportDate;
        this.productStocks = new LinkedHashMap<>(productStocks == null ? Map.of() : productStocks);
    }

    public void storeProductStock(Product product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        productStocks.put(product, quantity);
    }

    public void increaseStock(Product product, int quantity) {
        productStocks.put(product, checkCurrentBalance(product) + quantity);
    }

    public void decreaseStock(Product product, int quantity) {
        int current = checkCurrentBalance(product);
        if (quantity > current) {
            throw new IllegalArgumentException("Not enough stock for report update.");
        }
        productStocks.put(product, current - quantity);
    }

    public int checkCurrentBalance(Product product) {
        return productStocks.getOrDefault(product, 0);
    }

    public void generateAllProductsReport() {
        printReport();
    }

    public void generateSelectedProductsReport(List<Product> selectedProducts) {
        printSelected(selectedProducts);
    }

    public void printReport() {
        printSelected(List.copyOf(productStocks.keySet()));
    }

    private void printSelected(List<Product> selectedProducts) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("=".repeat(70));
        System.out.println("                    INVENTORY REPORT");
        System.out.println("=".repeat(70));
        System.out.printf("Warehouse   : %s%n", warehouse.getWarehouseName());
        System.out.printf("Report date : %s%n", reportDate.format(fmt));
        System.out.println("-".repeat(70));
        System.out.printf("%-10s | %-20s | %-10s | %-10s%n", "Code", "Product", "Balance", "Unit");
        System.out.println("-".repeat(70));
        for (Product product : selectedProducts) {
            Integer qty = productStocks.get(product);
            if (qty != null) {
                System.out.printf("%-10s | %-20s | %-10d | %-10s%n",
                        product.getProductCode(), product.getProductName(), qty, product.getUnitOfMeasure());
            }
        }
        System.out.println("=".repeat(70));
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public Map<Product, Integer> getProductStocks() {
        return Map.copyOf(productStocks);
    }
}
