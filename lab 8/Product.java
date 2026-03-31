class Product {
    private String productBarCode;
    private String productName;

    public Product(String productBarCode, String productName) {
        this.productBarCode = productBarCode;
        this.productName = productName;
    }

    public String getProductBarcode() {
        return productBarCode;
    }

    public void setProductBarCode(String productBarCode) {
        this.productBarCode = productBarCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}