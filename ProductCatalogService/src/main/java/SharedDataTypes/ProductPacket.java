package SharedDataTypes;

import unb.microservices.PCS.Product;

public class ProductPacket {
    public int product_ID;
    public String name;
    public double cost;
    public int quantity;
    public String type;

    public ProductPacket(int productIn, String nameIn, double costIn, int quantityIn, String typeIn) {
        product_ID = productIn;
        name = nameIn;
        cost = costIn;
        quantity = quantityIn;
        type = typeIn;
    }
}
