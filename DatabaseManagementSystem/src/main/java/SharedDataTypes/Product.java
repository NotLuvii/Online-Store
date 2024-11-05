package SharedDataTypes;

public class Product {
    private final int product_ID;
    private String name;
    private double cost;
    private int quantity;
    private String category;

    public Product(int product_ID, String name, double cost, int quantity, String category){
        this.product_ID = product_ID;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.category = category;
    }

    public int getProduct_ID(){
        return product_ID;
    }

    public String getName(){
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
