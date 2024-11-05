package unb.microservices.PCS;

public interface ProductFactory {
    public Product createProduct(int product_ID, String name, double cost, int quantity, String category);
}
