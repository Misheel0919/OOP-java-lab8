import java.util.Objects;

public class Product {
    private String productCode;
    private String productName;
    private String unitOfMeasure;
    private int quantity;

    public Product(String productCode, String productName, String unitOfMeasure, int initialQuantity) {
        if (productCode == null || productCode.isBlank()) {
            throw new IllegalArgumentException("Product code cannot be empty.");
        }
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        if (unitOfMeasure == null || unitOfMeasure.isBlank()) {
            throw new IllegalArgumentException("Unit of measure cannot be empty.");
        }
        if (initialQuantity < 0) {
            throw new IllegalArgumentException("Initial quantity cannot be negative.");
        }
        this.productCode = productCode;
        this.productName = productName;
        this.unitOfMeasure = unitOfMeasure;
        this.quantity = initialQuantity;
    }

    public void addQuantity(int amount) {
        validatePositive(amount, "Added quantity");
        quantity += amount;
    }

    public void removeQuantity(int amount) {
        validatePositive(amount, "Issued quantity");
        if (amount > quantity) {
            throw new IllegalArgumentException("Not enough stock for product: " + productName);
        }
        quantity -= amount;
    }

    public int updateByCount(int actualQuantity) {
        if (actualQuantity < 0) {
            throw new IllegalArgumentException("Actual quantity cannot be negative.");
        }
        int difference = actualQuantity - quantity;
        quantity = actualQuantity;
        return difference;
    }

    private void validatePositive(int amount, String field) {
        if (amount <= 0) {
            throw new IllegalArgumentException(field + " must be greater than zero.");
        }
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        if (productCode == null || productCode.isBlank()) {
            throw new IllegalArgumentException("Product code cannot be empty.");
        }
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        this.productName = productName;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        if (unitOfMeasure == null || unitOfMeasure.isBlank()) {
            throw new IllegalArgumentException("Unit of measure cannot be empty.");
        }
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - balance: %d %s", productCode, productName, quantity, unitOfMeasure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(productCode, product.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode);
    }
}
