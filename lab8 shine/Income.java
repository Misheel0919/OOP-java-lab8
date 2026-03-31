import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Income {
    private String incomeNo;
    private LocalDate date;
    private String supplier;
    private List<ReceiptItem> items;

    public Income(String incomeNo, LocalDate date, String supplier, List<ReceiptItem> items) {
        if (incomeNo == null || incomeNo.isBlank()) {
            throw new IllegalArgumentException("Income number cannot be empty.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        if (supplier == null || supplier.isBlank()) {
            throw new IllegalArgumentException("Supplier cannot be empty.");
        }
        this.incomeNo = incomeNo;
        this.date = date;
        this.supplier = supplier;
        this.items = new ArrayList<>(items == null ? List.of() : items);
        if (this.items.isEmpty()) {
            throw new IllegalArgumentException("Income items cannot be empty.");
        }
    }

    public int getTotalQuantity() {
        return items.stream().mapToInt(ReceiptItem::getQuantity).sum();
    }

    public void printReceipt() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("=".repeat(60));
        System.out.println("                  INCOME RECEIPT");
        System.out.println("=".repeat(60));
        System.out.printf("Income No : %s%n", incomeNo);
        System.out.printf("Date      : %s%n", date.format(fmt));
        System.out.printf("Supplier  : %s%n", supplier);
        System.out.println("-".repeat(60));
        System.out.printf("%-20s | %-8s | %-10s%n", "Product", "Quantity", "Unit");
        System.out.println("-".repeat(60));
        for (ReceiptItem item : items) {
            System.out.printf("%-20s | %-8d | %-10s%n",
                    item.getProduct().getProductName(),
                    item.getQuantity(),
                    item.getProduct().getUnitOfMeasure());
        }
        System.out.println("-".repeat(60));
        System.out.printf("Total quantity: %d%n", getTotalQuantity());
        System.out.println("=".repeat(60));
    }

    public String getIncomeNo() {
        return incomeNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSupplier() {
        return supplier;
    }

    public List<ReceiptItem> getItems() {
        return List.copyOf(items);
    }
}
