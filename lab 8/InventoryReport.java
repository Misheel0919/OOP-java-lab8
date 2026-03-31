import java.time.LocalDate;
import java.util.List;
import java.util.Map;

class InventoryReport {
    private Warehouse warehouse;
    private LocalDate reportDate;
    private Map<Product, Integer> productStocks;

    public InventoryReport(Warehouse warehouse, LocalDate reportDate, Map<Product, Integer> productStocks) {
        this.warehouse = warehouse;
        this.reportDate = reportDate;
        this.productStocks = productStocks;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public Map<Product, Integer> getProductStocks() {
        return productStocks;
    }

    public void setProductStocks(Map<Product, Integer> productStocks) {
        this.productStocks = productStocks;
    }

    public void storeProductStock(Product product, int quantity) {
    }

    public void increaseStock(Product product, int quantity) {
    }

    public void decreaseStock(Product product, int quantity) {
    }

    public int checkCurrentBalance(Product product) {
        return 0;
    }

    public void generateAllProductsReport() {
    }

    public void generateSelectedProductsReport(List<Product> selectedProducts) {
    }

    public void printReport() {
    }
}