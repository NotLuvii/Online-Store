package unb.microservices.orderservice;

public class AddToOrderCommand extends Command{
    private Product item;
    @Override
    public void execute() {
        Command.getOrder().getItems().add(item);
    }

    @Override
    public void undo() {
        Command.getOrder().getItems().remove(item);
    }

    public void setItem(Product itm){
        item = itm;
    }
}
