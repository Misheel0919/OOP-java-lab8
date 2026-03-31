import java.time.LocalDate;

class StockCount {
    private Product product;
    private LocalDate countingDate;
    private int systemQuantity;
    private int actualQuantity;
    private int difference;

    public StockCount(Product product, LocalDate countingDate, int systemQuantity, int actualQuantity) {
        this.product = product;
        this.countingDate = countingDate;
        this.systemQuantity = systemQuantity;
        this.actualQuantity = actualQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getCountingDate() {
        return countingDate;
    }

    public void setCountingDate(LocalDate countingDate) {
        this.countingDate = countingDate;
    }

    public int getSystemQuantity() {
        return systemQuantity;
    }

    public void setSystemQuantity(int systemQuantity) {
        this.systemQuantity = systemQuantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public void calculateDifference() {
    }

    public void updateBalance(InventoryReport inventoryReport) {
    }
}