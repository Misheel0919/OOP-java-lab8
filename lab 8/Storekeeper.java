import java.time.LocalDate;
import java.util.List;

class Storekeeper {
    private String storekeeperId;
    private String name;
    private Warehouse warehouse;

    public Storekeeper(String storekeeperId, String name, Warehouse warehouse) {
        this.storekeeperId = storekeeperId;
        this.name = name;
        this.warehouse = warehouse;
    }

    public String getStorekeeperId() {
        return storekeeperId;
    }

    public void setStorekeeperId(String storekeeperId) {
        this.storekeeperId = storekeeperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Income receiveGoods(String incomeNo, LocalDate date, List<Receipt> items, String supplier) {
        return null;
    }

    public Expense issueGoods(String expenseNo, LocalDate date, List<Receipt> items, String receiver) {
        return null;
    }

    public InventoryReport viewInventoryReport(List<Product> selectedProducts) {
        return warehouse.getInventoryReport();
    }

    public StorekeeperReport viewStorekeeperReport(LocalDate ehlehOgnoo, LocalDate duusahOgnoo, List<Product> selectedProducts) {
        return null;
    }

    public StockCount counting(Product product, LocalDate countingDate, int actualQuantity) {
        return null;
    }
}