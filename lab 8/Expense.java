import java.time.LocalDate;
import java.util.List;

class Expense {
    private String expenseNo;
    private LocalDate date;
    private String receiver;
    private List<Receipt> items;

    public Expense(String expenseNo, LocalDate date, String receiver, List<Receipt> items) {
        this.expenseNo = expenseNo;
        this.date = date;
        this.receiver = receiver;
        this.items = items;
    }

    public String getExpenseNo() {
        return expenseNo;
    }

    public void setExpenseNo(String expenseNo) {
        this.expenseNo = expenseNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public List<Receipt> getItems() {
        return items;
    }

    public void setItems(List<Receipt> items) {
        this.items = items;
    }

    public void printReceipt() {
    }
}