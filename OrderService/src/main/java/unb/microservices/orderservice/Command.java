package unb.microservices.orderservice;

import java.util.ArrayList;

public abstract class Command {
    private static Order order;

    public static void setCurrentOrder(Order currentOrder) {
        order = currentOrder;
    }

    public static Order getOrder() {
        return order;
    }

    public abstract void setItem(Product item);

    public abstract void execute() throws StatusChangingException, InsufficientFundsException;
    public abstract void undo() throws StatusChangingException, InsufficientFundsException;
}
