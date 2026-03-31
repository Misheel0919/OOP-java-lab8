import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Expense {
    private String expenseNo;
    private LocalDate date;
    private String receiver;
    private List<ReceiptItem> items;

    public Expense(String expenseNo, LocalDate date, String receiver, List<ReceiptItem> items) {
        if (expenseNo == null || expenseNo.isBlank()) {
            throw new IllegalArgumentException("Expense number cannot be empty.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        if (receiver == null || receiver.isBlank()) {
            throw new IllegalArgumentException("Receiver cannot be empty.");
        }
        this.expenseNo = expenseNo;
        this.date = date;
        this.receiver = receiver;
        this.items = new ArrayList<>(items == null ? List.of() : items);
        if (this.items.isEmpty()) {
            throw new IllegalArgumentException("Expense items cannot be empty.");
        }
    }

    public int getTotalQuantity() {
        return items.stream().mapToInt(ReceiptItem::getQuantity).sum();
    }

    public void printReceipt() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("=".repeat(60));
        System.out.println("                  EXPENSE RECEIPT");
        System.out.println("=".repeat(60));
        System.out.printf("Expense No: %s%n", expenseNo);
        System.out.printf("Date      : %s%n", date.format(fmt));
        System.out.printf("Receiver  : %s%n", receiver);
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

    public String getExpenseNo() {
        return expenseNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getReceiver() {
        return receiver;
    }

    public List<ReceiptItem> getItems() {
        return List.copyOf(items);
    }
}
