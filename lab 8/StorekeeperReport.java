import java.time.LocalDate;
import java.util.List;
class StorekeeperReport {
    private Warehouse warehouse;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<StorekeeperReportRow> rows;

    public StorekeeperReport(Warehouse warehouse, LocalDate startDate, LocalDate endDate,
                           List<StorekeeperReportRow> rows) {
        this.warehouse = warehouse;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rows = rows;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<StorekeeperReportRow> getRows() {
        return rows;
    }

    public void setRows(List<StorekeeperReportRow> rows) {
        this.rows = rows;
    }

    public void generateAllProductsReport() {
    }

    public void generateSelectedProductsReport(List<Product> selectedProducts) {
    }

    public int calculateOpeningBalance(Product product) {
        return 0;
    }

    public int calculateTotalReceipt(Product product) {
        return 0;
    }

    public int calculateTotalIssue(Product product) {
        return 0;
    }

    public int calculateClosingBalance(Product product) {
        return 0;
    }

    public void printReport() {
    }
}