class StorekeeperReportRow {
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

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(int openingBalance) {
        this.openingBalance = openingBalance;
    }

    public int getTotalReceipt() {
        return totalReceipt;
    }

    public void setTotalReceipt(int totalReceipt) {
        this.totalReceipt = totalReceipt;
    }

    public int getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(int totalExpense) {
        this.totalExpense = totalExpense;
    }

    public int getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(int closingBalance) {
        this.closingBalance = closingBalance;
    }
}