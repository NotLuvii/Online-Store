package unb.microservices.PCS;

public class MeatProduct implements Product {
    private final int product_ID;
    private final String name;
    private final double cost;
    private final int quantity;
    private final String category;

    public MeatProduct(int product_ID, String name, double cost, int quantity, String category){
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
}
