import java.util.List;

class Warehouse {
    private String warehouseCode;
    private String warehouseName;
    private Storekeeper storekeeper;
    private InventoryReport inventoryReport;
    private List<Receipt> receipts;
    private List<Expense> expenses;

    public Warehouse(String warehouseCode, String warehouseName, Storekeeper storekeeper, InventoryReport inventoryReport, List<Receipt> receipts, List<Expense> expenses) {
        this.warehouseCode = warehouseCode;
        this.warehouseName = warehouseName;
        this.storekeeper = storekeeper;
        this.inventoryReport = inventoryReport;
        this.receipts = receipts;
        this.expenses = expenses;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Storekeeper getStorekeeper() {
        return storekeeper;
    }

    public void setStorekeeper(Storekeeper storekeeper) {
        this.storekeeper = storekeeper;
    }

    public InventoryReport getInventoryReport() {
        return inventoryReport;
    }

    public void setInventoryReport(InventoryReport inventoryReport) {
        this.inventoryReport = inventoryReport;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public void updateProductStock(Product product, int quantity) {
    }

    public int findProductStock(Product product) {
        return 0;
    }
}