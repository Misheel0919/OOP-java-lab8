import java.time.LocalDate;
import java.util.List;

class Income {
    private String incomeNo;
    private LocalDate date;
    private String supplier;
    private List<Receipt> items;

    public Income(String incomeNo, LocalDate date, String supplier, List<Receipt> items) {
        this.incomeNo = incomeNo;
        this.date = date;
        this.supplier = supplier;
        this.items = items;
    }

    public String getIncomeNo () {
        return incomeNo;
    }

    public void setIncomeNo(String incomeNo) {
        this.incomeNo = incomeNo;
    } 

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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