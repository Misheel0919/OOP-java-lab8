public class StorekeeperReportRow {
    private Product product;
    private int openingBalance;
    private int totalReceipt;
    private int totalExpense;
    private int closingBalance;

    public StorekeeperReportRow(Product product, int openingBalance, int totalReceipt,
                                int totalExpense, int closingBalance) {
        this.product = product;
        this.openingBalance = openingBalance;
        this.totalReceipt = totalReceipt;
        this.totalExpense = totalExpense;
        this.closingBalance = closingBalance;
    }

    public Product getProduct() {
        return product;
    }

    public int getOpeningBalance() {
        return openingBalance;
    }

    public int getTotalReceipt() {
        return totalReceipt;
    }

    public int getTotalExpense() {
        return totalExpense;
    }

    public int getClosingBalance() {
        return closingBalance;
    }
}
