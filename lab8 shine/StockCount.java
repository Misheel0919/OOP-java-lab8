import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StockCount {
    private static int counter = 1;

    private String countNo;
    private Product product;
    private LocalDate countingDate;
    private int systemQuantity;
    private int actualQuantity;
    private int difference;

    public StockCount(Product product, LocalDate countingDate, int systemQuantity, int actualQuantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (countingDate == null) {
            throw new IllegalArgumentException("Counting date cannot be null.");
        }
        if (systemQuantity < 0 || actualQuantity < 0) {
            throw new IllegalArgumentException("Quantities cannot be negative.");
        }
        this.countNo = String.format("SC-%04d", counter++);
        this.product = product;
        this.countingDate = countingDate;
        this.systemQuantity = systemQuantity;
        this.actualQuantity = actualQuantity;
        calculateDifference();
    }

    public void calculateDifference() {
        this.difference = actualQuantity - systemQuantity;
    }

    public void updateBalance(InventoryReport inventoryReport) {
        product.updateByCount(actualQuantity);
        if (inventoryReport != null) {
            inventoryReport.storeProductStock(product, actualQuantity);
        }
    }

    public void printReport() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("=".repeat(60));
        System.out.println("               STOCK COUNT REPORT");
        System.out.println("=".repeat(60));
        System.out.printf("Count No   : %s%n", countNo);
        System.out.printf("Date       : %s%n", countingDate.format(fmt));
        System.out.printf("Product    : %s (%s)%n", product.getProductName(), product.getProductCode());
        System.out.printf("System Qty : %d%n", systemQuantity);
        System.out.printf("Actual Qty : %d%n", actualQuantity);
        System.out.printf("Difference : %+d%n", difference);
        System.out.println("=".repeat(60));
    }

    public String getCountNo() {
        return countNo;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDate getCountingDate() {
        return countingDate;
    }

    public int getSystemQuantity() {
        return systemQuantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public int getDifference() {
        return difference;
    }
}
