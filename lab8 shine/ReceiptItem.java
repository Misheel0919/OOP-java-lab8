public class ReceiptItem {
    private Product product;
    private int quantity;

    public ReceiptItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public void applyIncome() {
        product.addQuantity(quantity);
    }

    public void applyExpense() {
        product.removeQuantity(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%-20s | %5d %s", product.getProductName(), quantity, product.getUnitOfMeasure());
    }
}
