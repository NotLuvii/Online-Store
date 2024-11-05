package unb.microservices.orderservice;

import java.util.ArrayList;

public class ListWrapper {
    private ArrayList<Product> object;

    public ListWrapper(ArrayList<Product> object){
        this.object = object;
    }

    public ArrayList<Product> getObject() {
        return object;
    }
}
