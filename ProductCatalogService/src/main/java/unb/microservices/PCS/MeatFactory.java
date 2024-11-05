package unb.microservices.PCS;

public class MeatFactory implements ProductFactory{
    @Override
    public Product createProduct(int product_ID, String name, double cost, int quantity, String category) {
        return new MeatProduct(product_ID, name, cost, quantity, category);
    }
}
