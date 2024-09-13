package enums;

public enum ProductStatus {
    EXISTS("Product already exists");

    private String label;

    private ProductStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}